package com.fmt.kotlin.eyepetizer.player.ext

import androidx.databinding.BindingAdapter
import cn.jzvd.JzvdStd

@BindingAdapter(value = ["url","title"])
fun JzvdStd.load(url: String, title: String) {
    setUp(url, title)
    startVideo()
}