package com.fmt.kotlin.eyepetizer.player.util

import com.fmt.kotlin.eyepetizer.common.ext.MMKVExt
import com.fmt.kotlin.eyepetizer.common.ext.fromJson
import com.fmt.kotlin.eyepetizer.common.ext.toJson
import com.fmt.kotlin.eyepetizer.provider.model.Data
import com.google.gson.reflect.TypeToken

object VideoWatchManager {

    private const val VIDEO_WATCH_RECORD = "video_watch_record"
    private var videoWatchJson by MMKVExt(VIDEO_WATCH_RECORD,"")

    fun addVideoWatchRecord(data: Data) {
        videoWatchJson = if (videoWatchJson.isNullOrEmpty()) {
            val videoList = mutableListOf<Data>()
            videoList.add(data)
            toJson(videoList)
        } else {
            val videoList: MutableList<Data> =
                fromJson(videoWatchJson!!, object : TypeToken<MutableList<Data>>() {}.type)

            if (!videoList.any {
                    it.id == data.id
                }) {
                videoList.add(data)
            }
            toJson(videoList)
        }
    }

    fun removeVideoWatchRecord(data: Data) {
        val videoList: MutableList<Data> =
            fromJson(videoWatchJson!!, object : TypeToken<MutableList<Data>>() {}.type)
        videoList.remove(data)
        videoWatchJson = toJson(videoList)
    }

    fun getVideoWatchList(): MutableList<Data> {
        return if (videoWatchJson.isNullOrEmpty()) {
            mutableListOf()
        } else {
            fromJson(videoWatchJson!!, object : TypeToken<MutableList<Data>>() {}.type)
        }
    }

}