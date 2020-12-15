package com.fmt.kotlin.eyepetizer.player.util

import com.fmt.kotlin.eyepetizer.common.ext.dp2px
import com.fmt.kotlin.eyepetizer.common.ext.getScreenH
import com.fmt.kotlin.eyepetizer.common.ext.getScreenW

object BlurredUrlCreator {

    fun buildBlurredUrl(blurred: String): String {
        val width: Int = getScreenW()
        val height: Int = getScreenH() - dp2px(250f)
        return "${blurred}/thumbnail/${height}x${width}"
    }
}