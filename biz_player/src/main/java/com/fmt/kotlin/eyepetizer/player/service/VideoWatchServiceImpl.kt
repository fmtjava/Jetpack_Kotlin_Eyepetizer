package com.fmt.kotlin.eyepetizer.player.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.fmt.kotlin.eyepetizer.player.util.VideoWatchManager
import com.fmt.kotlin.eyepetizer.provider.model.Data
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath
import com.fmt.kotlin.eyepetizer.provider.service.VideoWatchService

@Route(path = RouterPath.Video.PATH_VIDEO_WATCH)
class VideoWatchServiceImpl : VideoWatchService {

    override fun getVideoWatchList(): MutableList<Data> = VideoWatchManager.getVideoWatchList()

    override fun removeVideoWatchRecord(data: Data) {
        VideoWatchManager.removeVideoWatchRecord(data)
    }

    override fun init(context: Context?) {
    }
}