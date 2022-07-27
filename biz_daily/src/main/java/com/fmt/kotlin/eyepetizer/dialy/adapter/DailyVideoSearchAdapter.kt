package com.fmt.kotlin.eyepetizer.dialy.adapter

import android.app.Activity
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.dialy.R
import com.fmt.kotlin.eyepetizer.dialy.databinding.DailySearchVideoBinding
import com.fmt.kotlin.eyepetizer.provider.model.Item
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity

class DailyVideoSearchAdapter(private val mActivity: Activity) :
    BaseQuickAdapter<Item, BaseViewHolder>(R.layout.daily_search_video),
    LoadMoreModule {

    init {
        addChildClickViewIds(R.id.iv_cover)
        setOnItemChildClickListener { adapter, view, position ->
            val itemData = data[position].data
            if (view.id == R.id.iv_cover) {
                go2VideoPlayerActivity(
                    mActivity,
                    view,
                    itemData
                )
            }
        }
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<DailySearchVideoBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: Item) {
        val binding = DataBindingUtil.getBinding<DailySearchVideoBinding>(holder.itemView)
        binding?.model = item
    }
}