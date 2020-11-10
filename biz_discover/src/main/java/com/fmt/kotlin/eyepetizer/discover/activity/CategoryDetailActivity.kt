package com.fmt.kotlin.eyepetizer.discover.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmt.kotlin.eyepetizer.common.base.activity.BaseBindActivity
import com.fmt.kotlin.eyepetizer.common.ext.immersionStatusBar
import com.fmt.kotlin.eyepetizer.common.ext.setToolBar
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverActivityCategoryDetailBinding
import com.fmt.kotlin.eyepetizer.discover.viewmodel.CategoryViewModel
import com.fmt.kotlin.eyepetizer.provider.adapter.RankListAdapter
import kotlinx.android.synthetic.main.discover_activity_category_detail.*

class CategoryDetailActivity :
    BaseBindActivity<DiscoverActivityCategoryDetailBinding, CategoryViewModel>() {

    private val mAdapter by lazy { RankListAdapter(this) }

    private var mId: Int = 0

    override val getLayoutRes: Int
        get() = R.layout.discover_activity_category_detail

    companion object {
        const val ID = "id"
        const val TITLE = "title"
        const val HEADER_IMAGE = "headerImage"
        fun start(context: Context, id: Int, title: String, headerImage: String) {
            val intent = Intent(context, CategoryDetailActivity::class.java)
            intent.putExtra(ID, id)
            intent.putExtra(TITLE, title)
            intent.putExtra(HEADER_IMAGE, headerImage)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        immersionStatusBar()
        setToolBar(mToolbar)

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
        mAdapter.loadMoreModule.setOnLoadMoreListener {
            getCategoryDetail(-1)
        }
        mRecyclerView.adapter = mAdapter
    }

    override fun initData() {
        mId = intent.getIntExtra(ID, 0)
        mBind.title = intent.getStringExtra(TITLE).toString()
        mBind.headerImage = intent.getStringExtra(HEADER_IMAGE).toString()
        getCategoryDetail(mId)
    }

    private fun getCategoryDetail(id: Int = -1) {
        mViewModel.getCategoryDetailList(id).observe(this, {
            if (it.isNotEmpty()) {
                mAdapter.loadMoreModule.loadMoreComplete()
                mAdapter.addData(it)
            } else {
                mAdapter.loadMoreModule.loadMoreEnd()
            }
        })
    }

    override fun handlerError() {
        mAdapter.loadMoreModule.loadMoreFail()
    }
}