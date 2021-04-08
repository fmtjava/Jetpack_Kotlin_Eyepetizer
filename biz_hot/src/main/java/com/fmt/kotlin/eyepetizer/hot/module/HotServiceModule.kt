package com.fmt.kotlin.eyepetizer.hot.module

import com.fmt.kotlin.eyepetizer.hot.api.HotApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HotServiceModule {

    @Singleton
    @Provides
    fun provideHotService(retrofit: Retrofit): HotApi {
        return retrofit.create(HotApi::class.java)
    }
}