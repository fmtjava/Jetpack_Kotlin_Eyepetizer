package com.fmt.kotlin.eyepetizer.dialy.adapter

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.fmt.kotlin.eyepetizer.dialy.model.ProviderMultiModel

class DailyAdapter(activity: Activity, owner: LifecycleOwner) :
    BaseProviderMultiAdapter<ProviderMultiModel>(), LoadMoreModule {

    init {
        addItemProvider(BannerItemProvider(owner, activity))
        addItemProvider(HeadTextItemProvider())
        addItemProvider(ImageTextProvider(activity))
    }

    override fun getItemType(data: List<ProviderMultiModel>, position: Int): Int =
        data[position].type
}