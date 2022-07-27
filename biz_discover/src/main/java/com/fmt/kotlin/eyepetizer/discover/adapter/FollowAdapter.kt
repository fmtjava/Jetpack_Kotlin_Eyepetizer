package com.fmt.kotlin.eyepetizer.discover.adapter

import android.app.Activity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.common.ext.dp2px
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemFollowBinding
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemWorksBinding
import com.fmt.kotlin.eyepetizer.provider.model.Item
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity

class FollowAdapter(private val mActivity: Activity) :
    BaseQuickAdapter<Item, BaseViewHolder>(R.layout.discover_item_follow), LoadMoreModule {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<DiscoverItemFollowBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: Item) {
        val binding = DataBindingUtil.getBinding<DiscoverItemFollowBinding>(holder.itemView)
        binding?.model = item
        binding?.activity = mActivity
    }

    class FollowWorksAdapter(private val activity: Activity) :
        BaseQuickAdapter<Item, BaseViewHolder>(R.layout.discover_item_works) {

        init {
            addChildClickViewIds(R.id.iv_cover)
            setOnItemChildClickListener { adapter, view, position ->
                val itemData = data[position].data
                if (view.id == R.id.iv_cover) {
                    go2VideoPlayerActivity(
                        activity,
                        view,
                        itemData
                    )
                }
            }
        }

        override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
            DataBindingUtil.bind<DiscoverItemWorksBinding>(viewHolder.itemView)
        }

        override fun convert(holder: BaseViewHolder, item: Item) {
            val binding = DataBindingUtil.getBinding<DiscoverItemWorksBinding>(holder.itemView)
            binding?.model = item
            if (data.indexOf(item) == data.size - 1) {
                binding?.llCover?.setPadding(dp2px(15f), 0, dp2px(15f), 0)
            }
        }
    }
}

@BindingAdapter(value = ["dataList", "activity"])
fun RecyclerView.setHorizontallyData(dataList: List<Item>, activity: Activity) {
    layoutManager =
        object :
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false) {
            override fun canScrollVertically(): Boolean = false
        }
    adapter = FollowAdapter.FollowWorksAdapter(activity).also { followAdapter ->
        followAdapter.addData(dataList)
    }
}