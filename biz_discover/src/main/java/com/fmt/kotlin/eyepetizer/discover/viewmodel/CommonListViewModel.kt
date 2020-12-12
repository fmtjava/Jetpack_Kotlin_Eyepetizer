package com.fmt.kotlin.eyepetizer.discover.viewmodel

import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel

abstract class CommonListViewModel : BaseViewModel() {

    var mNextPageUrl: String? = null

    fun <M> getListData(firstPage: Boolean): LiveData<MutableList<M>> = liveDataEx {
        if (mNextPageUrl == null && !firstPage) {
            mutableListOf()
        } else {
            if (firstPage) {
                getRefreshList()
            } else {
                getLoadMoreList()
            }
        }
    }

    abstract suspend fun <M> getRefreshList(): MutableList<M>

    abstract suspend fun <M> getLoadMoreList(): MutableList<M>

}