package com.fmt.kotlin.eyepetizer.player.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.player.R
import com.fmt.kotlin.eyepetizer.provider.databinding.ItemRelateVideoBinding
import com.fmt.kotlin.eyepetizer.provider.model.Item

class VideoRelateItemProvider : BaseItemProvider<Item>() {

    override val itemViewType: Int
        get() = VideoRelateAdapter.TYPE_VIDEO
    override val layoutId: Int
        get() = R.layout.item_relate_video

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ItemRelateVideoBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: Item) {
        val binding =
            DataBindingUtil.getBinding<ItemRelateVideoBinding>(helper.itemView)
        binding?.model = item.data
    }
}