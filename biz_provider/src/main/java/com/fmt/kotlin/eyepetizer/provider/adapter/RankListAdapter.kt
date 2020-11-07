package com.fmt.kotlin.eyepetizer.provider.adapter

import android.app.Activity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.common.util.ShareUtils
import com.fmt.kotlin.eyepetizer.provider.R
import com.fmt.kotlin.eyepetizer.provider.databinding.ItemRankBinding
import com.fmt.kotlin.eyepetizer.provider.model.Item
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity

class RankListAdapter(private val mActivity: Activity) :
    BaseQuickAdapter<Item, BaseViewHolder>(R.layout.item_rank), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: Item) {
        val bindingHolder = BaseDataBindingHolder<ItemRankBinding>(holder.itemView)
        bindingHolder.dataBinding?.model = item
        bindingHolder.dataBinding?.ivCover?.setOnClickListener { ivCover ->
            go2VideoPlayerActivity(
                mActivity,
                ivCover,
                item.data
            )
        }
        bindingHolder.dataBinding?.tvShare?.setOnClickListener {
            ShareUtils.shareText(mActivity, item.data.playUrl)
        }
    }
}