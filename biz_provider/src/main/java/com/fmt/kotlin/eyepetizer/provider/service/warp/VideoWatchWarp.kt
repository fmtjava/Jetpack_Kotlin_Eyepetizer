package com.fmt.kotlin.eyepetizer.provider.service.warp

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.fmt.kotlin.eyepetizer.provider.model.Data
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath
import com.fmt.kotlin.eyepetizer.provider.service.VideoWatchService

/**
 * 跨组件调用时外界无需知道Service初始化的细节，进行包装
 */
object VideoWatchWarp {

    @Autowired(name = RouterPath.Video.PATH_VIDEO_WATCH)
    lateinit var mVideoWatchService: VideoWatchService

    init {
        ARouter.getInstance().inject(this)
    }

    fun getVideoWatchList(): MutableList<Data> = mVideoWatchService.getVideoWatchList()

    fun removeVideoWatchRecord(data: Data) {
        mVideoWatchService.removeVideoWatchRecord(data)
    }
}