package com.fmt.kotlin.eyepetizer.dialy.adapter

import android.app.Activity
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.common.util.ShareUtils
import com.fmt.kotlin.eyepetizer.dialy.R
import com.fmt.kotlin.eyepetizer.dialy.databinding.DailyItemImageTextBinding
import com.fmt.kotlin.eyepetizer.dialy.model.ProviderMultiModel
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity

class ImageTextProvider(private val mActivity: Activity) : BaseItemProvider<ProviderMultiModel>() {

    override val itemViewType: Int
        get() = ProviderMultiModel.Type.TYPE_IMAGE

    override val layoutId: Int
        get() = R.layout.daily_item_image_text

    override fun convert(helper: BaseViewHolder, model: ProviderMultiModel) {
        val bindingHolder = BaseDataBindingHolder<DailyItemImageTextBinding>(helper.itemView)
        bindingHolder.dataBinding?.model = model
        bindingHolder.dataBinding?.ivCover?.setOnClickListener {
            go2VideoPlayerActivity(
                mActivity,
                it,
                model.item?.data!!
            )
        }
        bindingHolder.dataBinding?.tvShare?.setOnClickListener {
            ShareUtils.shareText(mActivity, model.item!!.data.playUrl)
        }
    }
}