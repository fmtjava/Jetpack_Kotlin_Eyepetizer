package com.fmt.kotlin.eyepetizer.player.observer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import cn.jzvd.Jzvd

class JZVDObserver : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_PAUSE){
            Jzvd.releaseAllVideos()
        }
    }
}