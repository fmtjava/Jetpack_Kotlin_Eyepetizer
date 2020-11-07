package com.fmt.kotlin.eyepetizer.hot.api

import com.fmt.kotlin.eyepetizer.hot.model.TabInfo
import com.fmt.kotlin.eyepetizer.provider.model.Issue
import retrofit2.http.GET
import retrofit2.http.Url

interface HotApi {

    @GET("v4/rankList")
    suspend fun getHotTabList(): TabInfo

    @GET
    suspend fun getRankList(@Url url: String): Issue

}