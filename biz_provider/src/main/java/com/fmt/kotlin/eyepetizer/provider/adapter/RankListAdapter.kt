package com.fmt.kotlin.eyepetizer.provider.adapter

import android.app.Activity
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.common.util.ShareUtils
import com.fmt.kotlin.eyepetizer.provider.R
import com.fmt.kotlin.eyepetizer.provider.databinding.ItemRankBinding
import com.fmt.kotlin.eyepetizer.provider.model.Item
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity

class RankListAdapter(private val mActivity: Activity) :
    BaseQuickAdapter<Item, BaseViewHolder>(R.layout.item_rank), LoadMoreModule {

    init {
        addChildClickViewIds(R.id.iv_cover, R.id.tv_share)
        setOnItemChildClickListener { adapter, view, position ->
            val itemData = data[position].data
            when (view.id) {
                R.id.iv_cover -> {
                    go2VideoPlayerActivity(
                        mActivity,
                        view,
                        itemData
                    )
                }
                R.id.tv_share -> {
                    ShareUtils.shareText(mActivity, itemData.playUrl)
                }
            }
        }
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ItemRankBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: Item) {
        val binding = DataBindingUtil.getBinding<ItemRankBinding>(holder.itemView)
        binding?.model = item
    }
}