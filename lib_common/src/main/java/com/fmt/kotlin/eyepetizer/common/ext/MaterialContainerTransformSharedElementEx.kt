package com.fmt.kotlin.eyepetizer.common.ext

import android.app.Activity
import android.graphics.Color
import android.view.View
import com.fmt.kotlin.eyepetizer.common.R
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

//通用容器变换动画配置
fun Activity.commonMaterialContainerTransformConfig() {
    //设置页面进入的过渡动画
    window.sharedElementEnterTransition = MaterialContainerTransform().apply {
        addTarget(android.R.id.content)
        duration = 250L
        setAllContainerColors(Color.WHITE)//Activity B 可能没有设置背景，跳转的时候有可能看到底层，效果不太好看
    }
    //设置页面退出的过渡动画
    window.sharedElementReturnTransition = MaterialContainerTransform().apply {
        addTarget(android.R.id.content)// startView 可能没有背景，透明的不太好看，可以加个这个
        duration = 250L
        setAllContainerColors(Color.WHITE)
    }
    //设置过渡元素控件以及名称
    findViewById<View>(android.R.id.content).transitionName =
        getString(R.string.shared_element_container)
    //设置共享元素页面进入回调监听
    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
}