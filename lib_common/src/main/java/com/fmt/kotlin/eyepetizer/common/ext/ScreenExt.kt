package com.fmt.kotlin.eyepetizer.common.ext

import android.content.Context
import android.view.WindowManager
import com.fmt.kotlin.eyepetizer.common.util.AppGlobal

fun getScreenW(): Int {
    val wm = AppGlobal.get()?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
        wm.currentWindowMetrics.bounds.width()
    } else {
        wm.defaultDisplay.width
    }
}

fun getScreenH(): Int {
    val wm = AppGlobal.get()?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
        wm.currentWindowMetrics.bounds.height()
    } else {
        wm.defaultDisplay.height
    }
}

fun getStatusBarHeight(): Int {
    var result = 0
    val resourceId =
        AppGlobal.get()?.resources?.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId != null && resourceId > 0) {
        result = AppGlobal.get()?.resources?.getDimensionPixelSize(resourceId)!!
    }
    return result
}