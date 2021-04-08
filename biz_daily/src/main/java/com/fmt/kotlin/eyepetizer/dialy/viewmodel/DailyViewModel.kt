package com.fmt.kotlin.eyepetizer.dialy.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.dialy.api.DailyApi
import com.fmt.kotlin.eyepetizer.dialy.model.ProviderMultiModel

class DailyViewModel @ViewModelInject constructor(private val mDailyApi: DailyApi) : BaseViewModel() {

    private val BANNER_TYPE = "banner2"
    private val TEXT_HEADER_TYPE = "textHeader"
    private var mNextPageUrl: String? = null


    fun getDailyBanner(): LiveData<ProviderMultiModel> =
        liveDataEx {
            val dailyModel = mDailyApi.getDailyBanner()
            mNextPageUrl = dailyModel.nextPageUrl
            val list = dailyModel.issueList[0].itemList
            list.removeAll {
                it.type == BANNER_TYPE
            }
            val providerMultiModel =
                ProviderMultiModel(type = ProviderMultiModel.Type.TYPE_BANNER, items = list)
            providerMultiModel
        }

    fun getDailyList(): LiveData<List<ProviderMultiModel>> =
        liveDataEx {
            if (mNextPageUrl == null) {
                mutableListOf()
            } else {
                val dailyModel = mDailyApi.getDailyList(mNextPageUrl!!)
                mNextPageUrl = dailyModel.nextPageUrl
                val list = dailyModel.issueList[0].itemList
                list.removeAll {
                    it.type == BANNER_TYPE
                }
                val providerMultiModels = mutableListOf<ProviderMultiModel>()
                list.forEach {
                    if (it.type == TEXT_HEADER_TYPE) {
                        providerMultiModels.add(
                            ProviderMultiModel(
                                type = ProviderMultiModel.Type.TYPE_TITLE,
                                item = it
                            )
                        )
                    } else {
                        providerMultiModels.add(
                            ProviderMultiModel(
                                type = ProviderMultiModel.Type.TYPE_IMAGE,
                                item = it
                            )
                        )
                    }
                }
                providerMultiModels
            }
        }
}