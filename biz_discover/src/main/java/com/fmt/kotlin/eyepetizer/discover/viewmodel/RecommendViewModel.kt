package com.fmt.kotlin.eyepetizer.discover.viewmodel

import com.fmt.kotlin.eyepetizer.discover.model.RecommendModel
import com.fmt.kotlin.eyepetizer.discover.service.DiscoverService

class RecommendViewModel : CommonListViewModel() {

    private val HORIZONTAL_SCROLL_CARD = "horizontalScrollCard"

    override suspend fun <M> getRefreshList(): List<M> = transList(DiscoverService.getRecList())

    override suspend fun <M> getLoadMoreList(): List<M> =
        transList(DiscoverService.getRecList(mNextPageUrl!!))

    private fun <M> transList(recommendModel: RecommendModel): List<M> {
        mNextPageUrl = recommendModel.nextPageUrl
        recommendModel.itemList.removeAll {
            it.type == HORIZONTAL_SCROLL_CARD
        }
        return recommendModel.itemList as List<M>
    }
}