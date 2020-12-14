package com.fmt.kotlin.eyepetizer.discover.viewmodel

import com.fmt.kotlin.eyepetizer.discover.model.NewsItemModel
import com.fmt.kotlin.eyepetizer.discover.model.NewsModel
import com.fmt.kotlin.eyepetizer.discover.model.NewsProviderMultiModel
import com.fmt.kotlin.eyepetizer.discover.service.DiscoverService

class NewsViewModel : CommonListViewModel() {

    val TEXT_CARD_TYPE = "textCard"

    override suspend fun <M> getRefreshList(): List<M> = transToMultiList(DiscoverService.getNewList())

    override suspend fun <M> getLoadMoreList(): List<M>  = transToMultiList(DiscoverService.getNewList(mNextPageUrl!!))

    private fun <M> transToMultiList(newsModel:NewsModel): List<M> {
        mNextPageUrl = "${newsModel.nextPageUrl}&vc=6030000&deviceModel=Android"
        return transToNewsProviderMultiList(newsModel.itemList) as List<M>
    }

    private fun transToNewsProviderMultiList(itemList: List<NewsItemModel>): List<NewsProviderMultiModel> {
        return itemList.map {
            if (it.type == TEXT_CARD_TYPE) {
                NewsProviderMultiModel(NewsProviderMultiModel.Type.TYPE_TITLE, it)
            } else {
                NewsProviderMultiModel(NewsProviderMultiModel.Type.TYPE_CONTENT, it)
            }
        }
    }
}