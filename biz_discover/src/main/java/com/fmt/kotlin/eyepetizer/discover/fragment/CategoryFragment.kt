package com.fmt.kotlin.eyepetizer.discover.fragment

import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.fmt.kotlin.eyepetizer.common.base.fragment.BaseVMFragment
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.activity.CategoryDetailActivity
import com.fmt.kotlin.eyepetizer.discover.adapter.CategoryAdapter
import com.fmt.kotlin.eyepetizer.discover.viewmodel.CategoryViewModel
import com.fondesa.recyclerviewdivider.dividerBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.discover_category_fragment.*

@AndroidEntryPoint
class CategoryFragment : BaseVMFragment<CategoryViewModel>(), OnItemClickListener {

    private val mAdapter by lazy { CategoryAdapter() }
    override val getLayoutRes: Int
        get() = R.layout.discover_category_fragment

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
        mRecyclerView.layoutManager = GridLayoutManager(context, 2)
        mAdapter.setOnItemClickListener(this)
        mRecyclerView.adapter = mAdapter
        mActivity.dividerBuilder()
            .asSpace()
            .showSideDividers()
            .size(5, TypedValue.COMPLEX_UNIT_DIP).build().addTo(mRecyclerView)
    }

    override fun lazyLoadData() {
        mViewModel.getCategoryList().observe(viewLifecycleOwner, {
            mAdapter.setList(it)
        })
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        CategoryDetailActivity.start(
            mActivity,
            mAdapter.data[position].id,
            mAdapter.data[position].name,
            mAdapter.data[position].headerImage,
            view.findViewById(R.id.fl_content)
        )
    }
}