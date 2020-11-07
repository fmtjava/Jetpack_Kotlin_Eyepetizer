package com.fmt.kotlin.eyepetizer.discover.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.fmt.kotlin.eyepetizer.common.base.fragment.BaseVMFragment
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.adapter.FollowAdapter
import com.fmt.kotlin.eyepetizer.discover.viewmodel.FollowViewModel
import kotlinx.android.synthetic.main.discover_fragment_common_refresh.*

class FollowFragment : BaseVMFragment<FollowViewModel>() {

    private var mIsLoadMore = false
    private val mAdapter by lazy { FollowAdapter(mActivity) }
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
            getFollowList()
        }
        mRecyclerView.adapter = mAdapter
    }

    override fun lazyLoadData() {
        getFollowList(true)
    }

    private fun getFollowList(firstPage: Boolean = false) {
        mViewModel.getFollowList(firstPage).observe(viewLifecycleOwner, {
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