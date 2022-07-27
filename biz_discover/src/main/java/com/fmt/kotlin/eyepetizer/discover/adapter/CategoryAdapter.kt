package com.fmt.kotlin.eyepetizer.discover.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemCategoryBinding
import com.fmt.kotlin.eyepetizer.discover.model.CategoryModel

class CategoryAdapter : BaseQuickAdapter<CategoryModel, BaseViewHolder>(R.layout.discover_item_category) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
       DataBindingUtil.bind<DiscoverItemCategoryBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: CategoryModel) {
        val binding = DataBindingUtil.getBinding<DiscoverItemCategoryBinding>(holder.itemView)
        binding?.categoryModel = item
    }
}