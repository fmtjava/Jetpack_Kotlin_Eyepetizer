package com.fmt.kotlin.eyepetizer.discover.fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.common.ext.toJson
import com.fmt.kotlin.eyepetizer.discover.activity.RecommendVideoAndPhotoActivity
import com.fmt.kotlin.eyepetizer.discover.adapter.RecommendAdapter
import com.fmt.kotlin.eyepetizer.discover.model.Item
import com.fmt.kotlin.eyepetizer.discover.viewmodel.RecommendViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecommendFragment : CommonListFragment<RecommendViewModel, Item>() {

    override fun getAdapter(): BaseQuickAdapter<Item, BaseViewHolder> = RecommendAdapter()

    override fun getLayoutManager(): RecyclerView.LayoutManager =
        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        RecommendVideoAndPhotoActivity.start(mActivity, toJson(mAdapter.data[position]))
    }
}