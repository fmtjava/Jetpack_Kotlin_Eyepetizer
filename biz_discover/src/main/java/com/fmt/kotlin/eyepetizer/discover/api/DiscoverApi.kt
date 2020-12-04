package com.fmt.kotlin.eyepetizer.discover.api

import com.fmt.kotlin.eyepetizer.discover.model.CategoryModel
import com.fmt.kotlin.eyepetizer.discover.model.TopicModel
import com.fmt.kotlin.eyepetizer.provider.model.Issue
import retrofit2.http.GET
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

}