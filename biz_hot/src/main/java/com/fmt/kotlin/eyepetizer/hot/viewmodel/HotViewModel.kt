package com.fmt.kotlin.eyepetizer.hot.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.hot.api.HotApi
import com.fmt.kotlin.eyepetizer.hot.model.TabInfo
import com.fmt.kotlin.eyepetizer.provider.model.Item

class HotViewModel @ViewModelInject constructor(private val mHotApi: HotApi) : BaseViewModel() {

    fun getHotTabList(): LiveData<TabInfo> = liveDataEx {
        mHotApi.getHotTabList()
    }

    fun getRankList(apiUrl: String): LiveData<MutableList<Item>> = liveDataEx {
        mHotApi.getRankList(apiUrl).itemList
    }

}