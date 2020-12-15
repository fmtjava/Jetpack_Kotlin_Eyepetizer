package com.fmt.kotlin.eyepetizer.discover.adapter

import android.app.Activity
import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.fmt.kotlin.eyepetizer.discover.model.NewsProviderMultiModel

class NewsAdapter(mActivity: Activity) : BaseProviderMultiAdapter<NewsProviderMultiModel>(), LoadMoreModule {

    init {
        addItemProvider(NewsTitleProvider())
        addItemProvider(NewsContentProvider(mActivity))
    }

    override fun getItemType(data: List<NewsProviderMultiModel>, position: Int): Int =
        data[position].type
}