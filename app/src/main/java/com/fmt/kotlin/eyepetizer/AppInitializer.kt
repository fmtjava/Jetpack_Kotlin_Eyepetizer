package com.fmt.kotlin.eyepetizer

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter
import com.fmt.kotlin.eyepetizer.common.global.Configurator
import com.tencent.mmkv.MMKV

class AppInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
        }
        Configurator.instance.withWebApiHost("http://baobab.kaiyanapp.com/api/").configure()
        MMKV.initialize(context)
        ARouter.init(context.applicationContext as Application)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}