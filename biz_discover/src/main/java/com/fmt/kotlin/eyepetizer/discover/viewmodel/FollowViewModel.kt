package com.fmt.kotlin.eyepetizer.discover.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.fmt.kotlin.eyepetizer.discover.api.DiscoverApi


class FollowViewModel @ViewModelInject constructor(private val mDiscoverApi: DiscoverApi) :CommonListViewModel() {

    override suspend fun <M> getRefreshList(): List<M> {
        val issue = mDiscoverApi.getFollowList()
        mNextPageUrl = issue.nextPageUrl
        return issue.itemList as List<M>
    }

    override suspend fun <M> getLoadMoreList(): List<M> {
        val issue = mDiscoverApi.getCategoryDetailList(mNextPageUrl!!)
        mNextPageUrl = issue.nextPageUrl
        return issue.itemList as List<M>
    }
}