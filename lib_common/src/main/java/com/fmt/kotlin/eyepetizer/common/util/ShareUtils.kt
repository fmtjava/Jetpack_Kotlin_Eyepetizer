package com.fmt.kotlin.eyepetizer.common.util

import android.content.Context
import android.content.Intent

object ShareUtils {

    fun shareText(context: Context, text: String) {
        var shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND //设置分享行为
        shareIntent.type = "text/plain" //设置分享内容的类型
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        shareIntent = Intent.createChooser(shareIntent, "")
        context.startActivity(shareIntent)
    }
}