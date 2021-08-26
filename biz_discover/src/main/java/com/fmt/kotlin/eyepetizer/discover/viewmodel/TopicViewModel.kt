package com.fmt.kotlin.eyepetizer.discover.viewmodel

import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.discover.api.DiscoverApi
import com.fmt.kotlin.eyepetizer.discover.model.TopicDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(private val mDiscoverApi: DiscoverApi) : CommonListViewModel() {

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