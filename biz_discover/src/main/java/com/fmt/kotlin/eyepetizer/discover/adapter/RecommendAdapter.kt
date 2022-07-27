package com.fmt.kotlin.eyepetizer.discover.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.common.ext.dp2px
import com.fmt.kotlin.eyepetizer.common.ext.getScreenW
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemRecommendBinding
import com.fmt.kotlin.eyepetizer.discover.model.Item

class RecommendAdapter : BaseQuickAdapter<Item, BaseViewHolder>(R.layout.discover_item_recommend),
    LoadMoreModule {

    private val mMaxImageWidth = (getScreenW() - dp2px(12f)) / 2

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<DiscoverItemRecommendBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: Item) {
        val binding = DataBindingUtil.getBinding<DiscoverItemRecommendBinding>(holder.itemView)
        binding!!.model = item
        binding.ivCover.layoutParams.width = mMaxImageWidth
        binding.ivCover.layoutParams.height =
            calculateImageHeight(item.data.content.data.width, item.data.content.data.height)
    }

    /**
     * 根据屏幕比例计算图片高
     * @param originalWidth   服务器图片原始尺寸：宽
     * @param originalHeight  服务器图片原始尺寸：高
     */
    private fun calculateImageHeight(originalWidth: Int, originalHeight: Int): Int {
        //服务器数据异常处理
        if (originalWidth == 0 || originalHeight == 0) {
            return mMaxImageWidth
        }
        return mMaxImageWidth * originalHeight / originalWidth
    }
}