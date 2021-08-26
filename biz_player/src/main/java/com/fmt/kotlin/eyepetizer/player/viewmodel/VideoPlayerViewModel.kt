package com.fmt.kotlin.eyepetizer.player.viewmodel

import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.player.adapter.VideoRelateAdapter
import com.fmt.kotlin.eyepetizer.player.api.VideoPlayerApi
import com.fmt.kotlin.eyepetizer.provider.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(private val mVideoPlayerApi: VideoPlayerApi) : BaseViewModel() {

    private val VIDEO_SMALL_CARD_TYPE = "videoSmallCard"

    fun getRelateVideoList(id: Int): LiveData<List<Item>> = liveDataEx {
        val issue = mVideoPlayerApi.getRelateVideoList(id)
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