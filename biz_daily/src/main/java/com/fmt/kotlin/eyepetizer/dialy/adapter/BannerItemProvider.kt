package com.fmt.kotlin.eyepetizer.dialy.adapter

import android.app.Activity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.dialy.R
import com.fmt.kotlin.eyepetizer.dialy.databinding.DailyItemBannerBinding
import com.fmt.kotlin.eyepetizer.dialy.model.ProviderMultiModel
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator

class BannerItemProvider(private val owner: LifecycleOwner, private val activity: Activity) :
    BaseItemProvider<ProviderMultiModel>() {

    override val itemViewType: Int
        get() = ProviderMultiModel.Type.TYPE_BANNER

    override val layoutId: Int
        get() = R.layout.daily_item_banner

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<DailyItemBannerBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: ProviderMultiModel) {
        val binding = DataBindingUtil.getBinding<DailyItemBannerBinding>(helper.itemView)
        binding?.model = item
        binding?.owner = owner
        binding?.activity = activity
    }

    companion object {

        @JvmStatic//由于kotlin中没有静态方法，所以使用@JvmStatic来标记这个方法就是静态方法
        @BindingAdapter(value = ["data", "owner", "activity"])
        fun setBannerData(
            banner: Banner<*, *>,
            data: ProviderMultiModel,
            owner: LifecycleOwner,
            activity: Activity
        ) {
            banner.apply {
                adapter = BannerImageAdapter(banner.context, data.items)
                addBannerLifecycleObserver(owner)
                indicator = CircleIndicator(banner.context)
                setOnBannerListener { _, position ->
                    go2VideoPlayerActivity(
                        activity,
                        null,
                        data.items[position].data.content.data,
                        true
                    )
                }
            }
        }
    }
}
