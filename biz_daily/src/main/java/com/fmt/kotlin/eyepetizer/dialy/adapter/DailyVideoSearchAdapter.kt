package com.fmt.kotlin.eyepetizer.dialy.adapter

import android.app.Activity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.dialy.R
import com.fmt.kotlin.eyepetizer.dialy.databinding.DailySearchVideoBinding
import com.fmt.kotlin.eyepetizer.provider.model.Item
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity

class DailyVideoSearchAdapter(private val mActivity: Activity) :
    BaseQuickAdapter<Item, BaseViewHolder>(R.layout.daily_search_video),
    LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: Item) {
        val bindingHolder = BaseDataBindingHolder<DailySearchVideoBinding>(holder.itemView)
        bindingHolder.dataBinding?.model = item
        bindingHolder.dataBinding?.ivCover?.setOnClickListener { ivCover ->
            go2VideoPlayerActivity(
                mActivity,
                ivCover,
                item.data
            )
        }
    }
}