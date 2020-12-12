package com.fmt.kotlin.eyepetizer.discover.viewmodel

import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.discover.model.TopicDetailModel
import com.fmt.kotlin.eyepetizer.discover.service.DiscoverService

class TopicViewModel : CommonListViewModel() {

    fun getTopicDetail(id: Int): LiveData<TopicDetailModel> = liveDataEx {
        DiscoverService.getTopicDetail(id)
    }

    override suspend fun <M> getRefreshList(): MutableList<M> {
        val topicModel = DiscoverService.getTopicList()
        mNextPageUrl = topicModel.nextPageUrl
        return topicModel.itemList as  MutableList<M>
    }

    override suspend fun <M> getLoadMoreList(): MutableList<M> {
        val topicModel = DiscoverService.getTopicList(
            mNextPageUrl!!
        )
        mNextPageUrl = topicModel.nextPageUrl
        return topicModel.itemList as  MutableList<M>
    }

}