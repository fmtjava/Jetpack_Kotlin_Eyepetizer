package com.fmt.kotlin.eyepetizer.dialy.activity

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.tu.loadingdialog.LoadingDailog
import com.fmt.kotlin.eyepetizer.common.base.activity.BaseBindVMActivity
import com.fmt.kotlin.eyepetizer.common.ext.commonMaterialContainerTransformConfig
import com.fmt.kotlin.eyepetizer.common.ext.hideInputKeyboard
import com.fmt.kotlin.eyepetizer.common.ext.immersionStatusBar
import com.fmt.kotlin.eyepetizer.dialy.R
import com.fmt.kotlin.eyepetizer.dialy.adapter.DailyVideoSearchAdapter
import com.fmt.kotlin.eyepetizer.dialy.databinding.DailySearchActivityBinding
import com.fmt.kotlin.eyepetizer.dialy.viewmodel.DailySearchViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.daily_search_activity.*

@AndroidEntryPoint
class DailySearchActivity : BaseBindVMActivity<DailySearchViewModel, DailySearchActivityBinding>() {

    private val mAdapter: DailyVideoSearchAdapter by lazy { DailyVideoSearchAdapter(this) }

    private val mLoadingDialog: LoadingDailog by lazy {
        LoadingDailog.Builder(this)
            .setMessage(getString(R.string.daily_loading_text))
            .create()
    }

    override val getLayoutRes: Int
        get() = R.layout.daily_search_activity

    companion object {
        fun start(context: Activity, startView: View) {
            Intent(context, DailySearchActivity::class.java).also {
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    context,
                    startView,
                    context.getString(R.string.shared_element_container)
                )
                context.startActivity(it, options.toBundle())
            }
        }
    }

    override fun initWindow() {
        commonMaterialContainerTransformConfig()
    }

    override fun initView() {
        immersionStatusBar(mTitleBar, android.R.color.white, true, 0.2f)
        mBackTv.setOnClickListener {
            onBackPressed()
        }
        initSearchEditText()
        initChip()
        initRecyclerView()
    }

    private fun initSearchEditText() {
        mSearchEt.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                v.hideInputKeyboard()
                val keyword = mSearchEt.text.toString()
                if (keyword.isNotEmpty()) {
                    showLoadingView(keyword)
                    getSearchVideo(keyword)
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun initChip() {
        mChip.setOnCloseIconClickListener {
            mSearchEt.isVisible = true
            mKeyWordLL.isVisible = true
            mSearchContainer.isVisible = false
            mChip.isVisible = false
            mSearchEt.setText("")
            mChip.text = ""
            mSearchEt.requestFocus()
        }
    }

    private fun initRecyclerView() {
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.itemAnimator = null
        mRecyclerView.setHasFixedSize(true)
        mAdapter.loadMoreModule.setOnLoadMoreListener {
            getSearchVideo(null)
        }
        mRecyclerView.adapter = mAdapter
    }

    private fun showLoadingView(keyword: String) {
        mChip.isVisible = true
        mChip.text = keyword
        mSearchEt.isVisible = false
        mLoadingDialog.show()
    }

    override fun initData() {
        mViewModel.getKeyWordList().observe(this) {
            initKeyWordView(it)
        }
    }

    private fun initKeyWordView(keyWordList: List<String>) {
        mChipGroup.removeAllViews()
        keyWordList.forEach { keyword ->
            val chip = layoutInflater.inflate(R.layout.daily_search_chip, mChipGroup, false) as Chip
            chip.text = keyword
            chip.setOnClickListener {
                showLoadingView(keyword)
                getSearchVideo(keyword)
            }
            mChipGroup.addView(chip)
        }
    }

    private fun getSearchVideo(keyword: String?) {
        mViewModel.searchVideoList(keyword).observerKt {
            if (it.isNotEmpty()) {
                mKeyWordLL.isVisible = false
                mEmptyLL.isVisible = false
                mSearchContainer.isVisible = true
                if (keyword != null) {
                    mSearchCountTv.text = String.format(
                        getString(R.string.daily_search_video_count),
                        keyword,
                        mViewModel.mTotal
                    )
                    mAdapter.setList(it)
                } else {
                    mAdapter.addData(it)
                    mAdapter.loadMoreModule.loadMoreComplete()
                }
            } else {
                mKeyWordLL.isVisible = false
                mSearchContainer.isVisible = false
                mEmptyLL.isVisible = true
                mAdapter.loadMoreModule.loadMoreComplete()
            }
        }
    }

    override fun hideLoading() {
        mLoadingDialog.dismiss()
    }
}