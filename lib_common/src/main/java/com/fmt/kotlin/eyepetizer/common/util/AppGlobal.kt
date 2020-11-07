package com.fmt.kotlin.eyepetizer.common.util

import android.app.Application
import java.lang.Exception

/**
 * 方便各个组件调用上下文对象
 */
object AppGlobal {

    private val mApplication: Application? = null

    fun get(): Application? {
        if (mApplication == null) {
            try {
                return Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication")
                    .invoke(null) as Application?

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return mApplication
    }
}