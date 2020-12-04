package com.fmt.kotlin.eyepetizer.discover.viewmodel

import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.discover.model.TopicItemModel
import com.fmt.kotlin.eyepetizer.discover.service.DiscoverService

class TopicViewModel : BaseViewModel() {

    private var mNextPageUrl: String? = null

    fun getTopicList(firstPage: Boolean): LiveData<MutableList<TopicItemModel>> = liveDataEx {
        if (mNextPageUrl == null && !firstPage) {
            mutableListOf()
        } else {
            val topicModel =
                if (firstPage) DiscoverService.getTopicList() else DiscoverService.getTopicList(
                    mNextPageUrl!!
                )
            mNextPageUrl = topicModel.nextPageUrl
            topicModel.itemList
        }
    }
}