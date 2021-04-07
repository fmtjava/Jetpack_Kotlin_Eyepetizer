package com.fmt.kotlin.eyepetizer.common.ext

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.fmt.kotlin.eyepetizer.common.util.AppGlobal

fun getVersionName(): String? {
    return getPackageInfo()?.versionName
}

private fun getPackageInfo(): PackageInfo? {
    var pi: PackageInfo? = null
    try {
        val pm: PackageManager? = AppGlobal.get()?.packageManager
        pi = AppGlobal.get()?.packageName?.let {
            pm?.getPackageInfo(
                it,
                PackageManager.GET_CONFIGURATIONS
            )
        }
        return pi
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return pi
}