package com.fmt.kotlin.eyepetizer.discover.activity

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmt.kotlin.eyepetizer.common.base.activity.BaseBindActivity
import com.fmt.kotlin.eyepetizer.common.ext.immersionStatusBar
import com.fmt.kotlin.eyepetizer.common.ext.setToolBar
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverActivityCategoryDetailBinding
import com.fmt.kotlin.eyepetizer.discover.viewmodel.CategoryViewModel
import com.fmt.kotlin.eyepetizer.provider.adapter.RankListAdapter
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
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
        fun start(context: Activity, id: Int, title: String, headerImage: String, startView: View) {
            val intent = Intent(context, CategoryDetailActivity::class.java).apply {
                putExtra(ID, id)
                putExtra(TITLE, title)
                putExtra(HEADER_IMAGE, headerImage)
            }
            val options = ActivityOptions.makeSceneTransitionAnimation(
                context,
                startView,
                context.getString(R.string.shared_element_container)
            )
            context.startActivity(intent, options.toBundle())
        }
    }

    override fun initWindow() {
        findViewById<View>(android.R.id.content).transitionName =
            getString(R.string.shared_element_container)
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 300L
            setAllContainerColors(Color.WHITE)//Activity B 可能没有设置背景，跳转的时候有可能看到底层，不太好看
        }
        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 250L
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