package com.fmt.kotlin.eyepetizer.discover.adapter

import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemNewsTitleBinding
import com.fmt.kotlin.eyepetizer.discover.model.NewsProviderMultiModel

class NewsTitleProvider : BaseItemProvider<NewsProviderMultiModel>() {

    override val itemViewType: Int
        get() = NewsProviderMultiModel.Type.TYPE_TITLE

    override val layoutId: Int
        get() = R.layout.discover_item_news_title

    override fun convert(helper: BaseViewHolder, item: NewsProviderMultiModel) {
        val bindingHolder = BaseDataBindingHolder<DiscoverItemNewsTitleBinding>(helper.itemView)
        bindingHolder.dataBinding?.model = item.newsItemModel
    }
}