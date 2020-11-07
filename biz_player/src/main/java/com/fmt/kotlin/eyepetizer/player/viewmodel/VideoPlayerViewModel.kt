package com.fmt.kotlin.eyepetizer.player.viewmodel

import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.player.adapter.VideoRelateAdapter
import com.fmt.kotlin.eyepetizer.player.service.VideoPlayerService
import com.fmt.kotlin.eyepetizer.provider.model.Item

class VideoPlayerViewModel : BaseViewModel() {

    private val VIDEO_SMALL_CARD_TYPE = "videoSmallCard"

    fun getRelateVideoList(id: Int): LiveData<List<Item>> = liveDataEx {
        val issue = VideoPlayerService.getRelateVideoList(id)
        issue.itemList.forEach {
            if (it.type == VIDEO_SMALL_CARD_TYPE) {
                it.itemType = VideoRelateAdapter.TYPE_VIDEO
            } else {
                it.itemType = VideoRelateAdapter.TYPE_TITLE
            }
        }
        issue.itemList
    }
}