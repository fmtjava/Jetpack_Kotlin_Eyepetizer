package com.fmt.kotlin.eyepetizer

import android.app.Application
import com.fmt.kotlin.eyepetizer.common.global.Configurator
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var mConfigurator: Configurator

    override fun onCreate() {
        super.onCreate()
        mConfigurator.withWebApiHost("http://baobab.kaiyanapp.com/api/").configure()
    }

}