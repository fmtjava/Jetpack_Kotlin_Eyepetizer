package com.fmt.kotlin.eyepetizer.common.module

import android.util.Log
import com.fmt.kotlin.eyepetizer.common.BuildConfig
import com.fmt.kotlin.eyepetizer.common.global.ConfigKeys
import com.fmt.kotlin.eyepetizer.common.global.Configurator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val TAG = AppModule::class.java.simpleName

    @Singleton
    @Provides
    fun provideConfigurator(): Configurator {
        return Configurator()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(configurator: Configurator): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            if (BuildConfig.DEBUG && configurator.getConfiguration(ConfigKeys.HTTP_LOG_ENABLE)) {
                Log.e(TAG, message)
            }
        }.also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(httpLoggingInterceptor)
                }
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, configurator: Configurator): Retrofit {
        return Retrofit.Builder()
            .baseUrl(configurator.getConfiguration<String>(ConfigKeys.WEB_API_HOST))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}