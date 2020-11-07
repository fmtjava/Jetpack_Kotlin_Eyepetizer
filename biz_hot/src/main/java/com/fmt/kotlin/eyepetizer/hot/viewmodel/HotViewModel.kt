package com.fmt.kotlin.eyepetizer.hot.viewmodel

import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.hot.model.TabInfo
import com.fmt.kotlin.eyepetizer.hot.service.HotService
import com.fmt.kotlin.eyepetizer.provider.model.Item

class HotViewModel : BaseViewModel() {

    fun getHotTabList(): LiveData<TabInfo> = liveDataEx {
        HotService.getHotTabList()
    }

    fun getRankList(apiUrl: String): LiveData<MutableList<Item>> = liveDataEx {
        HotService.getRankList(apiUrl).itemList
    }

}