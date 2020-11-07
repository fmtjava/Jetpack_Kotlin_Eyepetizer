package com.fmt.kotlin.eyepetizer.discover.viewmodel

import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.common.global.ConfigKeys
import com.fmt.kotlin.eyepetizer.common.global.Configurator
import com.fmt.kotlin.eyepetizer.discover.model.CategoryModel
import com.fmt.kotlin.eyepetizer.discover.service.DiscoverService
import com.fmt.kotlin.eyepetizer.provider.model.Item

class CategoryViewModel : BaseViewModel() {

    private val URL_END = "&udid=d2807c895f0348a180148c9dfa6f2feeac0781b5&deviceModel=Android"
    private var mNextPageUrl: String? = null

    fun getCategoryList(): LiveData<List<CategoryModel>> = liveDataEx {
        DiscoverService.getCategoryList()
    }

    fun getCategoryDetailList(id: Int): LiveData<List<Item>> = liveDataEx {
        if ((mNextPageUrl == null) and (id == -1)) {
            mutableListOf()
        } else {
            val url: String = if (id != -1) {
                "${Configurator.instance.getConfiguration<String>(ConfigKeys.WEB_API_HOST)}v4/categories/videoList?id=${id}${URL_END}"
            } else {
                "${mNextPageUrl}${URL_END}"
            }
            val issue = DiscoverService.getCategoryDetailList(url)
            mNextPageUrl = issue.nextPageUrl
            issue.itemList
        }
    }
}