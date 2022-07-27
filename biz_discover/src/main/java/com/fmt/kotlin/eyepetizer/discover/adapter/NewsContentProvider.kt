package com.fmt.kotlin.eyepetizer.discover.adapter

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.activity.NewsDetailActivity
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemNewsBinding
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemNewsContentBinding
import com.fmt.kotlin.eyepetizer.discover.model.NewsProviderMultiModel

class NewsContentProvider(private val mActivity:Activity) : BaseItemProvider<NewsProviderMultiModel>() {

    override val itemViewType: Int
        get() = NewsProviderMultiModel.Type.TYPE_CONTENT

    override val layoutId: Int
        get() = R.layout.discover_item_news_content

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<DiscoverItemNewsContentBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: NewsProviderMultiModel) {
        val binding = DataBindingUtil.getBinding<DiscoverItemNewsContentBinding>(helper.itemView)
        binding?.model = item.newsItemModel
    }

    override fun onClick(
        helper: BaseViewHolder,
        view: View,
        data: NewsProviderMultiModel,
        position: Int
    ) {
        val actionUrl = data.newsItemModel.data.actionUrl
        var url = Uri.decode(actionUrl.substring(actionUrl.indexOf("url")))
        url = url.substring(4)
        NewsDetailActivity.start(mActivity,url)
    }
}

@BindingAdapter(value = ["titleList"])
fun LinearLayout.setNewsTitleList(titleList: List<String>) {
    removeAllViews()
    titleList.forEach { title ->
        val binding = DiscoverItemNewsBinding.inflate(LayoutInflater.from(context), this, false)
        binding.title = title
        addView(binding.root)
    }
}