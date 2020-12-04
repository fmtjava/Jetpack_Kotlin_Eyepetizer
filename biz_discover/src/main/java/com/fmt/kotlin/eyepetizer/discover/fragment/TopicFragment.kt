package com.fmt.kotlin.eyepetizer.discover.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.fmt.kotlin.eyepetizer.common.base.fragment.BaseVMFragment
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.adapter.TopicAdapter
import com.fmt.kotlin.eyepetizer.discover.viewmodel.TopicViewModel
import kotlinx.android.synthetic.main.discover_fragment_common_refresh.*

class TopicFragment : BaseVMFragment<TopicViewModel>() {

    private var mIsLoadMore = false
    private val mAdapter by lazy { TopicAdapter() }

    override val getLayoutRes: Int
        get() = R.layout.discover_fragment_common_refresh

    override fun initView() {
        mSwipeRefreshLayout.isRefreshing = true
        mSwipeRefreshLayout.setOnRefreshListener {
            initData()
        }
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
        mAdapter.loadMoreModule.setOnLoadMoreListener {
            mIsLoadMore = true
            getTopicList()
        }
        mRecyclerView.adapter = mAdapter
    }

    override fun lazyLoadData() {
        getTopicList(true)
    }

    private fun getTopicList(firstPage: Boolean = false) {
        mViewModel.getTopicList(firstPage).observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                if (firstPage) {
                    mAdapter.setList(it)
                } else {
                    mAdapter.addData(it)
                    mAdapter.loadMoreModule.loadMoreComplete()
                }
            } else {
                mAdapter.loadMoreModule.loadMoreEnd()
            }
        })
    }

    override fun hideLoading() {
        mSwipeRefreshLayout.isRefreshing = false
    }

    override fun handlerError() {
        if (mIsLoadMore) {
            mAdapter.loadMoreModule.loadMoreFail()
        }
    }
}