package com.fmt.kotlin.eyepetizer.discover.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemTopicBinding
import com.fmt.kotlin.eyepetizer.discover.model.TopicItemModel

class TopicAdapter :
    BaseQuickAdapter<TopicItemModel, BaseViewHolder>(R.layout.discover_item_topic), LoadMoreModule {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<DiscoverItemTopicBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: TopicItemModel) {
        val binding = DataBindingUtil.getBinding<DiscoverItemTopicBinding>(holder.itemView)
        binding?.model = item
    }
}