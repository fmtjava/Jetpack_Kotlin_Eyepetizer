package com.fmt.kotlin.eyepetizer.player.util

import android.content.Context
import android.view.WindowManager
import com.fmt.kotlin.eyepetizer.common.ext.dp2px
import com.fmt.kotlin.eyepetizer.common.util.AppGlobal

object BlurredUrlCreator {

    fun buildBlurredUrl(blurred: String): String {
        val wm = AppGlobal.get()?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val width: Int
        val height: Int
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            width = wm.currentWindowMetrics.bounds.width()
            height = wm.currentWindowMetrics.bounds.height()
        } else {
            width = wm.defaultDisplay.width
            height = wm.defaultDisplay.height - dp2px(250f)
        }
        return "${blurred}/thumbnail/${height}x${width}"
    }

}