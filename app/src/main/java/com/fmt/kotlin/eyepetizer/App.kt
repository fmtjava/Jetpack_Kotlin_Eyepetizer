package com.fmt.kotlin.eyepetizer

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.fmt.kotlin.eyepetizer.common.global.Configurator
import com.tencent.mmkv.MMKV

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
        }
        Configurator.instance.withWebApiHost("http://baobab.kaiyanapp.com/api/").configure()
        MMKV.initialize(this)
        ARouter.init(this)
    }

}