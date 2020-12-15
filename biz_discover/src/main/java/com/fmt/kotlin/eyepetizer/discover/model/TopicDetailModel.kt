package com.fmt.kotlin.eyepetizer.discover.model

import com.fmt.kotlin.eyepetizer.provider.model.Data
import com.fmt.kotlin.eyepetizer.provider.model.Header

data class TopicDetailModel(
    val adTrack: Any,
    val brief: String,
    val count: Int,
    val headerImage: String,
    val id: Int,
    val itemList: List<Item>,
    val shareLink: String,
    val text: String
)

data class Item(
    val adIndex: Int,
    val `data`: ItemData,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class ItemData(
    val dataType: String,
    val header: Header,
    val content: Content,
)

data class Content(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)





