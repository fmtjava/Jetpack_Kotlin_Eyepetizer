package com.fmt.kotlin.eyepetizer.player.adapter

import android.app.Activity
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.player.R
import com.fmt.kotlin.eyepetizer.player.databinding.PlayerItemRelateVideoBinding
import com.fmt.kotlin.eyepetizer.provider.model.Item
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity

class VideoRelateItemProvider(private val mActivity: Activity) : BaseItemProvider<Item>() {

    override val itemViewType: Int
        get() = VideoRelateAdapter.TYPE_VIDEO
    override val layoutId: Int
        get() = R.layout.player_item_relate_video

    override fun convert(helper: BaseViewHolder, item: Item) {
        val baseDataBindingHolder =
            BaseDataBindingHolder<PlayerItemRelateVideoBinding>(helper.itemView)
        baseDataBindingHolder.dataBinding?.model = item.data
        baseDataBindingHolder.dataBinding?.root?.setOnClickListener {
            go2VideoPlayerActivity(
                mActivity,
                null,
                item.data,
                true
            )
        }
    }
}