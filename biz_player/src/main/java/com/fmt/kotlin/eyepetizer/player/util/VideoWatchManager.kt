package com.fmt.kotlin.eyepetizer.player.util

import com.fmt.kotlin.eyepetizer.common.ext.fromJson
import com.fmt.kotlin.eyepetizer.common.ext.toJson
import com.fmt.kotlin.eyepetizer.provider.model.Data
import com.google.gson.reflect.TypeToken
import com.tencent.mmkv.MMKV

object VideoWatchManager {

    private val mmkv = MMKV.defaultMMKV()
    private val VIDEO_WATCH_RECORD = "video_watch_record"

    fun addVideoWatchRecord(data: Data) {
        val json = mmkv.decodeString(VIDEO_WATCH_RECORD)
        if (json.isNullOrEmpty()) {
            val videoList = mutableListOf<Data>()
            videoList.add(data)

            mmkv.encode(VIDEO_WATCH_RECORD, toJson(videoList))
        } else {
            val videoList: MutableList<Data> =
                fromJson(json, object : TypeToken<MutableList<Data>>() {}.type)

            if (!videoList.any {
                    it.id == data.id
                }) {
                videoList.add(data)
            }
            mmkv.encode(VIDEO_WATCH_RECORD, toJson(videoList))
        }
    }

    fun removeVideoWatchRecord(data: Data) {
        val json = mmkv.decodeString(VIDEO_WATCH_RECORD)
        val videoList: MutableList<Data> =
            fromJson(json, object : TypeToken<MutableList<Data>>() {}.type)
        videoList.remove(data)
        mmkv.encode(VIDEO_WATCH_RECORD, toJson(videoList))
    }

    fun getVideoWatchList(): MutableList<Data> {
        val json = mmkv.decodeString(VIDEO_WATCH_RECORD)
        return if (json.isNullOrEmpty()) {
            mutableListOf()
        } else {
            fromJson(json, object : TypeToken<MutableList<Data>>() {}.type)
        }
    }

}