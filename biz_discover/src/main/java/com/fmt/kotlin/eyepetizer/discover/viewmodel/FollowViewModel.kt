package com.fmt.kotlin.eyepetizer.discover.viewmodel

import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.discover.service.DiscoverService
import com.fmt.kotlin.eyepetizer.provider.model.Item

class FollowViewModel : BaseViewModel() {

    private var mNextPageUrl: String? = null

    fun getFollowList(firstPage: Boolean): LiveData<MutableList<Item>> = liveDataEx {
        if (mNextPageUrl == null && !firstPage) {
            mutableListOf()
        } else {
            val issue =
                if (firstPage) DiscoverService.getFollowList() else DiscoverService.getCategoryDetailList(
                    mNextPageUrl!!
                )
            mNextPageUrl = issue.nextPageUrl
            issue.itemList
        }
    }

}