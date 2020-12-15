package com.fmt.kotlin.eyepetizer.discover.api

import com.fmt.kotlin.eyepetizer.discover.model.*
import com.fmt.kotlin.eyepetizer.provider.model.Issue
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface DiscoverApi {

    @GET("v4/categories")
    suspend fun getCategoryList(): List<CategoryModel>

    @GET("v4/tabs/follow")
    suspend fun getFollowList(): Issue

    @GET
    suspend fun getCategoryDetailList(@Url url: String): Issue

    @GET("v3/specialTopics")
    suspend fun getTopicList(): TopicModel

    @GET
    suspend fun getTopicList(@Url url: String): TopicModel

    @GET("v3/lightTopics/internal/{id}")
    suspend fun getTopicDetail(@Path("id") id: Int): TopicDetailModel

    @GET("v7/information/list?vc=6030000&deviceModel=Android")
    suspend fun getNewList(): NewsModel

    @GET
    suspend fun getNewList(@Url url: String): NewsModel

    @GET("v7/community/tab/rec")
    suspend fun getRecList(): RecommendModel

    @GET
    suspend fun getRecList(@Url url: String): RecommendModel

}