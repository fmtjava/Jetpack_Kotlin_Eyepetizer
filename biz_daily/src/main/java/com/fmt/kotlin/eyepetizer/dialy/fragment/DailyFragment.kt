package com.fmt.kotlin.eyepetizer.dialy.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.fmt.kotlin.eyepetizer.common.base.fragment.BaseVMFragment
import com.fmt.kotlin.eyepetizer.dialy.R
import com.fmt.kotlin.eyepetizer.dialy.activity.DailySearchActivity
import com.fmt.kotlin.eyepetizer.dialy.adapter.DailyAdapter
import com.fmt.kotlin.eyepetizer.dialy.viewmodel.DailyViewModel
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.daily_fragment.*

@AndroidEntryPoint
@Route(path = RouterPath.Daily.PATH_DAILY_HOME)
class DailyFragment : BaseVMFragment<DailyViewModel>() {

    private var mIsLoadMore = false

    private val mAdapter: DailyAdapter by lazy { DailyAdapter(mActivity, this) }
    override val getLayoutRes: Int
        get() = R.layout.daily_fragment

    override fun initView() {
        mSearchTv.setOnClickListener {
            DailySearchActivity.start(mActivity, it)
        }
        mSwipeRefreshLayout.isRefreshing = true
        mSwipeRefreshLayout.setOnRefreshListener {
            mIsLoadMore = false
            lazyLoadData()
        }
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mAdapter

        mAdapter.loadMoreModule.setOnLoadMoreListener {
            mIsLoadMore = true
            getDailyList()
        }
    }

    override fun hideLoading() {
        mSwipeRefreshLayout.isRefreshing = false
    }

    override fun lazyLoadData() {
        mViewModel.getDailyBanner().observerKt {
            mAdapter.setList(mutableListOf())
            mAdapter.addData(it)
        }
    }

    private fun getDailyList() {
          mViewModel.getDailyList().observerKt{
              if (it.isNotEmpty()) {
                  if (mIsLoadMore) {
                      mAdapter.addData(it)
                      mAdapter.loadMoreModule.loadMoreComplete()
                  } else {
                      mAdapter.setList(it)
                  }
              } else {
                  mAdapter.loadMoreModule.loadMoreEnd()
              }
          }
    }

    override fun handlerError() {
        if (mIsLoadMore) {
            mAdapter.loadMoreModule.loadMoreFail()
        }
    }
}