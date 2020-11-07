package com.fmt.kotlin.eyepetizer.discover.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemCategoryBinding
import com.fmt.kotlin.eyepetizer.discover.model.CategoryModel

class CategoryAdapter : BaseQuickAdapter<CategoryModel, BaseViewHolder>(R.layout.discover_item_category) {

    override fun convert(holder: BaseViewHolder, item: CategoryModel) {
        val bindingHolder = BaseDataBindingHolder<DiscoverItemCategoryBinding>(holder.itemView)
        bindingHolder.dataBinding?.categoryModel = item
    }
}