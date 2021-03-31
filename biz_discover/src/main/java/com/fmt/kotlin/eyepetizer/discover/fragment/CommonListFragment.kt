package com.fmt.kotlin.eyepetizer.discover.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.common.base.fragment.BaseVMFragment
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.viewmodel.CommonListViewModel
import kotlinx.android.synthetic.main.discover_fragment_common_refresh.*

abstract class CommonListFragment<VM : CommonListViewModel, M> : BaseVMFragment<VM>(),
    OnItemClickListener {

    private var mIsLoadMore = false
    lateinit var mAdapter: BaseQuickAdapter<M, BaseViewHolder>
    override val getLayoutRes: Int
        get() = R.layout.discover_fragment_common_refresh

    override fun initView() {
        mSwipeRefreshLayout.isRefreshing = true
        mSwipeRefreshLayout.setOnRefreshListener {
            lazyLoadData()
        }
        mRecyclerView.layoutManager = getLayoutManager()
        mRecyclerView.itemAnimator = null
        mRecyclerView.setHasFixedSize(true)//性能优化点，避免重复策略、布局、绘制
        mAdapter = getAdapter()
        mAdapter.setOnItemClickListener(this)
        mAdapter.loadMoreModule.setOnLoadMoreListener {
            mIsLoadMore = true
            getListData()
        }
        mRecyclerView.adapter = mAdapter
    }

    abstract fun getAdapter(): BaseQuickAdapter<M, BaseViewHolder>

    override fun lazyLoadData() {
        getListData(true)
    }

    private fun getListData(firstPage: Boolean = false) {
        mViewModel.getListData<M>(firstPage).observerKt{
            if (it.isNotEmpty()) {
                if (firstPage) {
                    mAdapter.setList(it)
                } else {
                    mAdapter.addData(it)
                    mAdapter.loadMoreModule.loadMoreComplete()
                }
            } else {
                if (mViewModel.mNextPageUrl == null) {
                    mAdapter.loadMoreModule.loadMoreEnd()
                } else {
                    mAdapter.loadMoreModule.loadMoreComplete()
                }
            }
        }
    }

    override fun hideLoading() {
        mSwipeRefreshLayout.isRefreshing = false
    }

    override fun handlerError() {
        if (mIsLoadMore) {
            mAdapter.loadMoreModule.loadMoreFail()
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }

    open fun getLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(context)
}