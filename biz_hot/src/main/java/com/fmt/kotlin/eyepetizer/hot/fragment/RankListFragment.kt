package com.fmt.kotlin.eyepetizer.hot.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmt.kotlin.eyepetizer.common.base.fragment.BaseVMFragment
import com.fmt.kotlin.eyepetizer.hot.R
import com.fmt.kotlin.eyepetizer.hot.viewmodel.HotViewModel
import com.fmt.kotlin.eyepetizer.provider.adapter.RankListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.hot_fragment_rank_list.*

@AndroidEntryPoint
class RankListFragment : BaseVMFragment<HotViewModel>() {

    private val mAdapter by lazy { RankListAdapter(mActivity) }

    companion object {
        const val API_URL_KEY = "api_url"
        fun newInstance(apiUrl: String): RankListFragment {
            val fragment = RankListFragment()
            val arguments = Bundle()
            arguments.putString(API_URL_KEY, apiUrl)
            fragment.arguments = arguments
            return fragment
        }
    }

    override val getLayoutRes: Int
        get() = R.layout.hot_fragment_rank_list

    override fun showLoading() {
        mSwipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        mSwipeRefreshLayout.isRefreshing = false
    }

    override fun initView() {
        mSwipeRefreshLayout.setOnRefreshListener {
            initData()
        }
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mAdapter
    }

    override fun lazyLoadData() {
        arguments?.getString(API_URL_KEY)?.let { apiUrl -> getRankList(apiUrl) }
    }

    private fun getRankList(apiUrl: String) {
        mViewModel.getRankList(apiUrl).observerKt {
            mAdapter.setList(it)
        }
    }

}