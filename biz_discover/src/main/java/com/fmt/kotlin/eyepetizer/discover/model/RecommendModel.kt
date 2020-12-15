package com.fmt.kotlin.eyepetizer.discover.model

data class RecommendModel(
    val adExist: Boolean,
    val count: Int,
    val itemList: MutableList<Item>,
    val nextPageUrl: String,
    val total: Int
)
