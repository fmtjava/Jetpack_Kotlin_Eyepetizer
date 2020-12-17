package com.fmt.kotlin.eyepetizer.dialy.viewmodel

import androidx.lifecycle.LiveData
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.dialy.service.DailyService
import com.fmt.kotlin.eyepetizer.provider.model.Issue
import com.fmt.kotlin.eyepetizer.provider.model.Item

class DailySearchViewModel : BaseViewModel() {

    private var mNextPageUrl: String? = null
    var mTotal: Int = 0

    fun getKeyWordList(): LiveData<List<String>> = flowEx { DailyService.getKeyWordList() }

    fun searchVideoList(keyword: String?): LiveData<List<Item>> = flowEx {
        if (keyword == null) {
            if (mNextPageUrl == null) {
                return@flowEx mutableListOf()
            } else {
                return@flowEx tranList(DailyService.getMoreSearchVideoList(mNextPageUrl!!))
            }
        } else {
            return@flowEx tranList(DailyService.searchVideoList(keyword))
        }
    }

    private fun tranList(issue: Issue): List<Item> {
        mNextPageUrl = issue.nextPageUrl
        mTotal = issue.total
        issue.itemList.removeAll { item ->
            item.data.cover == null
        }
        return issue.itemList
    }

}