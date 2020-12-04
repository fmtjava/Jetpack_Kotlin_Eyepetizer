package com.fmt.kotlin.eyepetizer.discover.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemTopicBinding
import com.fmt.kotlin.eyepetizer.discover.model.TopicItemModel

class TopicAdapter :
    BaseQuickAdapter<TopicItemModel, BaseViewHolder>(R.layout.discover_item_topic), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: TopicItemModel) {
        val bindingHolder = BaseDataBindingHolder<DiscoverItemTopicBinding>(holder.itemView)
        bindingHolder.dataBinding?.model = item
    }
}