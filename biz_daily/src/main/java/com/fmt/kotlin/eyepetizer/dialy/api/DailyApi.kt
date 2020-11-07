package com.fmt.kotlin.eyepetizer.dialy.api

import com.fmt.kotlin.eyepetizer.dialy.model.DailyModel
import retrofit2.http.GET
import retrofit2.http.Url

interface DailyApi {

    @GET("v2/feed?num=1")
    suspend fun getDailyBanner(): DailyModel

    @GET
    suspend fun getDailyList(@Url url: String): DailyModel


}