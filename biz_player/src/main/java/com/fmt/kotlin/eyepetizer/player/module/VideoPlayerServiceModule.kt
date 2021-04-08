package com.fmt.kotlin.eyepetizer.player.module

import com.fmt.kotlin.eyepetizer.player.api.VideoPlayerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VideoPlayerServiceModule {

    @Singleton
    @Provides
    fun provideVideoPlayerService(retrofit: Retrofit): VideoPlayerApi {
        return retrofit.create(VideoPlayerApi::class.java)
    }
}