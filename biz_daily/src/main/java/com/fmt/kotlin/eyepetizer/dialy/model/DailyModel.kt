package com.fmt.kotlin.eyepetizer.dialy.model

import com.fmt.kotlin.eyepetizer.provider.model.Item

data class DailyModel(
    val itemList: MutableList<DailyItem>,
    val nextPageUrl: String,
)

data class DailyItem(
    val type: String,
    val data: DailyItemData,
)

data class DailyItemData(
    val text: String,
    val content: Item,
)

