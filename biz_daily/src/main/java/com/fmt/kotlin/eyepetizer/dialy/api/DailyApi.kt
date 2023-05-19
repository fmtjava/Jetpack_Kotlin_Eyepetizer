package com.fmt.kotlin.eyepetizer.dialy.api

import com.fmt.kotlin.eyepetizer.dialy.model.DailyModel
import com.fmt.kotlin.eyepetizer.provider.model.Issue
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface DailyApi {

    @GET("v5/index/tab/feed")
    suspend fun getDailyBanner(): DailyModel

    @GET
    suspend fun getDailyList(@Url url: String): DailyModel

    @GET("v3/queries/hot")
    suspend fun getKeyWordList(): List<String>

    @GET("v1/search")
    suspend fun searchVideoList(@Query("query") keyword: String): Issue

    @GET
    suspend fun getMoreSearchVideoList(@Url url: String): Issue

}