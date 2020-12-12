package com.fmt.kotlin.eyepetizer.discover.viewmodel

import com.fmt.kotlin.eyepetizer.discover.service.DiscoverService

class FollowViewModel :CommonListViewModel() {

    override suspend fun <M> getRefreshList(): MutableList<M> {
        val issue = DiscoverService.getFollowList()
        mNextPageUrl = issue.nextPageUrl
        return issue.itemList as MutableList<M>
    }

    override suspend fun <M> getLoadMoreList(): MutableList<M> {
        val issue = DiscoverService.getCategoryDetailList(mNextPageUrl!!)
        mNextPageUrl = issue.nextPageUrl
        return issue.itemList as MutableList<M>
    }
}