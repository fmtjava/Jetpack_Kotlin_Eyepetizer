package com.fmt.kotlin.eyepetizer.dialy.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.dialy.R
import com.fmt.kotlin.eyepetizer.dialy.databinding.DailyItemBannerBinding
import com.fmt.kotlin.eyepetizer.dialy.model.ProviderMultiModel
import com.fmt.kotlin.eyepetizer.provider.constant.BaseConstant
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator

class BannerItemProvider(private val owner: LifecycleOwner) :
    BaseItemProvider<ProviderMultiModel>() {

    override val itemViewType: Int
        get() = ProviderMultiModel.Type.TYPE_BANNER

    override val layoutId: Int
        get() = R.layout.daily_item_banner

    override fun convert(helper: BaseViewHolder, item: ProviderMultiModel) {
        val baseDataBindingHolder = BaseDataBindingHolder<DailyItemBannerBinding>(helper.itemView)
        baseDataBindingHolder.dataBinding?.model = item
        baseDataBindingHolder.dataBinding?.owner = owner
    }

    companion object {

        @JvmStatic//由于kotlin中没有静态方法，所以使用@JvmStatic来标记这个方法就是静态方法
        @BindingAdapter(value = ["data", "owner"])
        fun setBannerData(banner: Banner<*, *>, data: ProviderMultiModel, owner: LifecycleOwner) {
            banner.apply {
                adapter = BannerImageAdapter(banner.context, data.items)
                addBannerLifecycleObserver(owner)
                indicator = CircleIndicator(banner.context)
                setOnBannerListener { _, position ->
                    ARouter.getInstance().build(RouterPath.Video.PATH_VIDEO_HOME)
                        .withSerializable(
                            BaseConstant.VIDEO_MODE_KEY,
                            data.items[position].data
                        )
                        .navigation()
                }
            }
        }
    }
}
