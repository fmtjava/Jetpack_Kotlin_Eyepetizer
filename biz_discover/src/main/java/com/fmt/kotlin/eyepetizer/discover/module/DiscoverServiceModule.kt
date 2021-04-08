package com.fmt.kotlin.eyepetizer.discover.module

import com.fmt.kotlin.eyepetizer.discover.api.DiscoverApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiscoverServiceModule {

    @Singleton
    @Provides
    fun provideDiscoverService(retrofit: Retrofit): DiscoverApi {
        return retrofit.create(DiscoverApi::class.java)
    }
}