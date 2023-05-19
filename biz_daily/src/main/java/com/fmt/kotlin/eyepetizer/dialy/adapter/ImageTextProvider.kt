package com.fmt.kotlin.eyepetizer.dialy.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.dialy.R
import com.fmt.kotlin.eyepetizer.dialy.databinding.DailyItemImageTextBinding
import com.fmt.kotlin.eyepetizer.dialy.model.ProviderMultiModel

class ImageTextProvider : BaseItemProvider<ProviderMultiModel>() {

    override val itemViewType: Int
        get() = ProviderMultiModel.Type.TYPE_IMAGE

    override val layoutId: Int
        get() = R.layout.daily_item_image_text

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<DailyItemImageTextBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: ProviderMultiModel) {
        val binding = DataBindingUtil.getBinding<DailyItemImageTextBinding>(helper.itemView)
        binding?.model = item.item?.data?.content
    }
}