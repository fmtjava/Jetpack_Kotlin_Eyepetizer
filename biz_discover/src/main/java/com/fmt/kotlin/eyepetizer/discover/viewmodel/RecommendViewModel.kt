package com.fmt.kotlin.eyepetizer.discover.viewmodel

import com.fmt.kotlin.eyepetizer.discover.api.DiscoverApi
import com.fmt.kotlin.eyepetizer.discover.model.RecommendModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(private val mDiscoverApi: DiscoverApi) :
    CommonListViewModel() {

    private val HORIZONTAL_SCROLL_CARD = "horizontalScrollCard"

    override suspend fun <M> getRefreshList(): List<M> = transList(mDiscoverApi.getRecList())

    override suspend fun <M> getLoadMoreList(): List<M> =
        transList(mDiscoverApi.getRecList(mNextPageUrl!!))

    private fun <M> transList(recommendModel: RecommendModel): List<M> {
        mNextPageUrl = recommendModel.nextPageUrl
        recommendModel.itemList.removeAll {
            it.type == HORIZONTAL_SCROLL_CARD
        }
        return recommendModel.itemList as List<M>
    }
}