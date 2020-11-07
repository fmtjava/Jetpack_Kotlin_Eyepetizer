package com.fmt.kotlin.eyepetizer.discover.adapter

import android.app.Activity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemFollowBinding
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemWorksBinding
import com.fmt.kotlin.eyepetizer.provider.model.Item
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity

class FollowAdapter(private val mActivity: Activity) :
    BaseQuickAdapter<Item, BaseViewHolder>(R.layout.discover_item_follow), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: Item) {
        val bindingHolder = BaseDataBindingHolder<DiscoverItemFollowBinding>(holder.itemView)
        bindingHolder.dataBinding?.model = item
        bindingHolder.dataBinding?.activity = mActivity
    }

    class FollowWorksAdapter(private val activity: Activity) :
        BaseQuickAdapter<Item, BaseViewHolder>(R.layout.discover_item_works) {

        override fun convert(holder: BaseViewHolder, item: Item) {
            val bindingHolder = BaseDataBindingHolder<DiscoverItemWorksBinding>(holder.itemView)
            bindingHolder.dataBinding?.model = item
            bindingHolder.dataBinding?.ivCover?.setOnClickListener {
                go2VideoPlayerActivity(
                    activity,
                    it,
                    item.data
                )
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