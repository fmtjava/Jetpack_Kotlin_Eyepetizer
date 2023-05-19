package com.fmt.kotlin.eyepetizer.dialy.adapter

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.fmt.kotlin.eyepetizer.common.util.ShareUtils
import com.fmt.kotlin.eyepetizer.dialy.R
import com.fmt.kotlin.eyepetizer.dialy.model.ProviderMultiModel
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity

class DailyAdapter(activity: Activity, owner: LifecycleOwner) :
    BaseProviderMultiAdapter<ProviderMultiModel>(), LoadMoreModule {

    init {
        addItemProvider(BannerItemProvider(owner, activity))
        addItemProvider(HeadTextItemProvider())
        addItemProvider(ImageTextProvider())
        addChildClickViewIds(R.id.iv_cover, R.id.tv_share)
        setOnItemChildClickListener { adapter, view, position ->
            val itemData = data[position]
            when(view.id){
                R.id.iv_cover -> {
                    go2VideoPlayerActivity(
                        activity,
                        view,
                        itemData.item?.data?.content?.data!!
                    )
                }
                R.id.tv_share -> {
                    ShareUtils.shareText(activity, itemData.item!!.data.content.data.playUrl)
                }
            }
        }
    }

    override fun getItemType(data: List<ProviderMultiModel>, position: Int): Int =
        data[position].type
}