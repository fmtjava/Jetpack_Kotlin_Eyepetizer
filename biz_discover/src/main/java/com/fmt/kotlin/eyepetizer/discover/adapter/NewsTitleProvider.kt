package com.fmt.kotlin.eyepetizer.discover.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemNewsTitleBinding
import com.fmt.kotlin.eyepetizer.discover.model.NewsProviderMultiModel

class NewsTitleProvider : BaseItemProvider<NewsProviderMultiModel>() {

    override val itemViewType: Int
        get() = NewsProviderMultiModel.Type.TYPE_TITLE

    override val layoutId: Int
        get() = R.layout.discover_item_news_title

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<DiscoverItemNewsTitleBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: NewsProviderMultiModel) {
        val binding = DataBindingUtil.getBinding<DiscoverItemNewsTitleBinding>(helper.itemView)
        binding?.model = item.newsItemModel
    }
}