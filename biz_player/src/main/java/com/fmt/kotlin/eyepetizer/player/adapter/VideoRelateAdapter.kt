package com.fmt.kotlin.eyepetizer.player.adapter

import android.app.Activity
import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.fmt.kotlin.eyepetizer.provider.model.Item
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity

class VideoRelateAdapter(mActivity: Activity) : BaseProviderMultiAdapter<Item>() {

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_VIDEO = 1
    }

    init {
        addItemProvider(VideoRelateTitleProvider())
        addItemProvider(VideoRelateItemProvider())
        setOnItemClickListener { adapter, view, position ->
            val itemData = data[position]
            go2VideoPlayerActivity(
                mActivity,
                null,
                itemData.data,
                true
            )
        }
    }

    override fun getItemType(data: List<Item>, position: Int): Int = data[position].itemType
}