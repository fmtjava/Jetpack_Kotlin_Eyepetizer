package com.fmt.kotlin.eyepetizer.discover.viewmodel

import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.common.global.ConfigKeys
import com.fmt.kotlin.eyepetizer.common.global.Configurator
import com.fmt.kotlin.eyepetizer.discover.api.DiscoverApi
import com.fmt.kotlin.eyepetizer.discover.model.CategoryModel
import com.fmt.kotlin.eyepetizer.provider.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val mDiscoverApi: DiscoverApi,
    private val mConfigurator: Configurator
) : BaseViewModel() {

    private val URL_END = "&udid=d2807c895f0348a180148c9dfa6f2feeac0781b5&deviceModel=Android"
    private var mNextPageUrl: String? = null

    fun getCategoryList(): LiveData<List<CategoryModel>> = liveDataEx {
        mDiscoverApi.getCategoryList()
    }

    fun getCategoryDetailList(id: Int): LiveData<List<Item>> = liveDataEx {
        if ((mNextPageUrl == null) and (id == -1)) {
            mutableListOf()
        } else {
            val url: String = if (id != -1) {
                "${mConfigurator.getConfiguration<String>(ConfigKeys.WEB_API_HOST)}v4/categories/videoList?id=${id}${URL_END}"
            } else {
                "${mNextPageUrl}${URL_END}"
            }
            val issue = mDiscoverApi.getCategoryDetailList(url)
            mNextPageUrl = issue.nextPageUrl
            issue.itemList
        }
    }
}