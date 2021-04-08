package com.fmt.kotlin.eyepetizer.discover.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.discover.api.DiscoverApi
import com.fmt.kotlin.eyepetizer.discover.model.TopicDetailModel

class TopicViewModel @ViewModelInject constructor(private val mDiscoverApi: DiscoverApi) : CommonListViewModel() {

    fun getTopicDetail(id: Int): LiveData<TopicDetailModel> = liveDataEx {
        mDiscoverApi.getTopicDetail(id)
    }

    override suspend fun <M> getRefreshList(): List<M> {
        val topicModel = mDiscoverApi.getTopicList()
        mNextPageUrl = topicModel.nextPageUrl
        return topicModel.itemList as List<M>
    }

    override suspend fun <M> getLoadMoreList(): List<M> {
        val topicModel = mDiscoverApi.getTopicList(
            mNextPageUrl!!
        )
        mNextPageUrl = topicModel.nextPageUrl
        return topicModel.itemList as List<M>
    }

}