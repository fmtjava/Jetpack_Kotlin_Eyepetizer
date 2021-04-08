package com.fmt.kotlin.eyepetizer.discover.fragment

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.activity.TopicDetailActivity
import com.fmt.kotlin.eyepetizer.discover.adapter.TopicAdapter
import com.fmt.kotlin.eyepetizer.discover.model.TopicItemModel
import com.fmt.kotlin.eyepetizer.discover.viewmodel.TopicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicFragment : CommonListFragment<TopicViewModel, TopicItemModel>() {

    override fun getAdapter(): BaseQuickAdapter<TopicItemModel, BaseViewHolder> = TopicAdapter()

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        TopicDetailActivity.start(
            mActivity,
            (mAdapter as TopicAdapter).data[position].data.id,
            view.findViewById(R.id.ll_content)
        )
    }
}