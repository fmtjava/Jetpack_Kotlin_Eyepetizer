package com.fmt.kotlin.eyepetizer.common.http

import android.util.Log
import com.fmt.kotlin.eyepetizer.common.BuildConfig
import com.fmt.kotlin.eyepetizer.common.global.ConfigKeys
import com.fmt.kotlin.eyepetizer.common.global.Configurator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 静态内部类方式实现单列模式
 */
class RetrofitClient private constructor() {

    private val TAG = RetrofitClient::class.java.simpleName

    companion object {
        val instance = SingleTonProvider.holder
    }

    private object SingleTonProvider {
        val holder = RetrofitClient()
    }

    private val httpLoggingInterceptor = HttpLoggingInterceptor(object :
        HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.e(TAG, message)
        }
    }).also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .also {
            if (BuildConfig.DEBUG) {
                it.addInterceptor(httpLoggingInterceptor)
            }
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Configurator.instance.getConfiguration<String>(ConfigKeys.WEB_API_HOST))
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

}