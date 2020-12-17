package com.fmt.kotlin.eyepetizer.common.ext

import android.app.Activity
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.FloatRange
import com.gyf.immersionbar.ktx.immersionBar

fun Activity.immersionStatusBar() {
    immersionBar {}
}

fun Activity.immersionStatusBar(
    fits: Boolean = true,
    @ColorRes statusBarColor: Int,
    isDarkFont: Boolean,
    @FloatRange(from = 0.0, to = 1.0) statusAlpha: Float
) {
    immersionBar {
        fitsSystemWindows(fits)
        statusBarColor(statusBarColor)
        statusBarDarkFont(isDarkFont, statusAlpha)
    }
}

fun Activity.immersionStatusBar(
    titleBar: View,
    @ColorRes statusBarColor: Int,
    isDarkFont: Boolean,
    @FloatRange(from = 0.0, to = 1.0) statusAlpha: Float
) {
    immersionBar {
        titleBar(titleBar)
        statusBarColor(statusBarColor)
        statusBarDarkFont(isDarkFont, statusAlpha)
    }
}