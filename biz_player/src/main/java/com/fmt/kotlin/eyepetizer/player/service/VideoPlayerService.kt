package com.fmt.kotlin.eyepetizer.player.service

import com.fmt.kotlin.eyepetizer.common.http.RetrofitClient
import com.fmt.kotlin.eyepetizer.player.api.VideoPlayerApi

object VideoPlayerService : VideoPlayerApi by RetrofitClient.create(VideoPlayerApi::class.java)