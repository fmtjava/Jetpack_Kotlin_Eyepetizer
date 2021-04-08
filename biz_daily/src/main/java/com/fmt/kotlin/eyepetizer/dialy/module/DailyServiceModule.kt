package com.fmt.kotlin.eyepetizer.dialy.module

import com.fmt.kotlin.eyepetizer.dialy.api.DailyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DailyServiceModule {

    @Singleton
    @Provides
    fun provideDailyService(retrofit: Retrofit): DailyApi {
        return retrofit.create(DailyApi::class.java)
    }
}