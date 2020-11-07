package com.fmt.kotlin.eyepetizer.player.api

import com.fmt.kotlin.eyepetizer.provider.model.Issue
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoPlayerApi {

    @GET("v4/video/related")
    suspend fun getRelateVideoList(@Query("id") id: Int): Issue


}