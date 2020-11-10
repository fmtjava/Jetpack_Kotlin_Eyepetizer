package com.fmt.kotlin.eyepetizer.common.util

import android.app.Application

/**
 * 方便各个组件调用上下文对象
 */
object AppGlobal {

    private var mApplication: Application? = null
        get() {
            if (field == null) {
                return Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication")
                    .invoke(null) as Application?
            }
            return field
        }

    fun get(): Application? = mApplication
}