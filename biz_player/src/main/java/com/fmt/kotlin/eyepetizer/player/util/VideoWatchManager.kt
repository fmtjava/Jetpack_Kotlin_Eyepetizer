package com.fmt.kotlin.eyepetizer.player.util

import com.fmt.kotlin.eyepetizer.provider.model.Data
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tencent.mmkv.MMKV

object VideoWatchManager {

    private val mmkv = MMKV.defaultMMKV()
    private val gson = Gson()
    private val VIDEO_WATCH_RECORD = "video_watch_record"

    fun addVideoWatchRecord(data: Data) {
        val json = mmkv.decodeString(VIDEO_WATCH_RECORD)
        if (json.isNullOrEmpty()) {
            val videoList = mutableListOf<Data>()
            videoList.add(data)

            mmkv.encode(VIDEO_WATCH_RECORD, gson.toJson(videoList))
        } else {
            val videoList: MutableList<Data> =
                gson.fromJson(json, object : TypeToken<MutableList<Data>>() {}.type)

            if (!videoList.any {
                    it.id == data.id
                }) {
                videoList.add(data)
            }
            mmkv.encode(VIDEO_WATCH_RECORD, gson.toJson(videoList))
        }
    }

    fun removeVideoWatchRecord(data: Data) {
        val json = mmkv.decodeString(VIDEO_WATCH_RECORD)
        val videoList: MutableList<Data> =
            gson.fromJson(json, object : TypeToken<MutableList<Data>>() {}.type)
        videoList.remove(data)
        mmkv.encode(VIDEO_WATCH_RECORD, gson.toJson(videoList))
    }

    fun getVideoWatchList(): MutableList<Data> {
        val json = mmkv.decodeString(VIDEO_WATCH_RECORD)
        return if (json.isNullOrEmpty()) {
            mutableListOf()
        } else {
            gson.fromJson(json, object : TypeToken<MutableList<Data>>() {}.type)
        }
    }

}