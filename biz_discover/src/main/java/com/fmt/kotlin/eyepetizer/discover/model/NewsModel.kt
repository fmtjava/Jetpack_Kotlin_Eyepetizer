package com.fmt.kotlin.eyepetizer.discover.model

data class NewsModel(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<NewsItemModel>,
    val nextPageUrl: String,
    val total: Int
)

data class NewsItemModel(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class Data(
    val actionUrl: String,
    val adTrack: Any,
    val backgroundImage: String,
    val bannerList: List<Banner>,
    val dataType: String,
    val follow: Any,
    val headerType: String,
    val id: Int,
    val startTime: Long,
    val subTitle: Any,
    val text: String,
    val titleList: List<String>,
    val type: String
)

data class Banner(
    val background_image: String,
    val link: String,
    val poster_image: String,
    val tag_name: String,
    val title: String
)