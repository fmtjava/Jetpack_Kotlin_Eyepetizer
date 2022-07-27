package com.fmt.kotlin.eyepetizer.player.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.player.R
import com.fmt.kotlin.eyepetizer.player.databinding.PlayerItemRelateTitleBinding
import com.fmt.kotlin.eyepetizer.provider.model.Item

class VideoRelateTitleProvider : BaseItemProvider<Item>() {
    override val itemViewType: Int
        get() = VideoRelateAdapter.TYPE_TITLE
    override val layoutId: Int
        get() = R.layout.player_item_relate_title

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<PlayerItemRelateTitleBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: Item) {
        val binding = DataBindingUtil.getBinding<PlayerItemRelateTitleBinding>(helper.itemView)
        binding?.model = item
    }

}