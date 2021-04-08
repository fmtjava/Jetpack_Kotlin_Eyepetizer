package com.fmt.kotlin.eyepetizer.discover.fragment

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.discover.adapter.NewsAdapter
import com.fmt.kotlin.eyepetizer.discover.model.NewsModel
import com.fmt.kotlin.eyepetizer.discover.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : CommonListFragment<NewsViewModel, NewsModel>() {

    override fun getAdapter(): BaseQuickAdapter<NewsModel, BaseViewHolder> =
        NewsAdapter(mActivity) as BaseQuickAdapter<NewsModel, BaseViewHolder>
}