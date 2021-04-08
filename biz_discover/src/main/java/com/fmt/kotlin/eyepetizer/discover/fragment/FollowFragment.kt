package com.fmt.kotlin.eyepetizer.discover.fragment

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.discover.adapter.FollowAdapter
import com.fmt.kotlin.eyepetizer.discover.viewmodel.FollowViewModel
import com.fmt.kotlin.eyepetizer.provider.model.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowFragment : CommonListFragment<FollowViewModel, Item>() {

    override fun getAdapter(): BaseQuickAdapter<Item, BaseViewHolder> = FollowAdapter(mActivity)
}