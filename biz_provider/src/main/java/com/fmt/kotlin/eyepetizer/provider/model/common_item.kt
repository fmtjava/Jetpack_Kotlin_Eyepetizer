package com.fmt.kotlin.eyepetizer.provider.model

data class Issue(
    val count: Int,
    val date: Long,
    val itemList: MutableList<Item>,
    val publishTime: Long,
    val releaseTime: Long,
    val type: String,
    val nextPageUrl: String
)

data class Item(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val type: String,
    var itemType: Int
)

data class Data(
    val actionUrl: String,
    val ad: Boolean,
    val author: Author?,
    val autoPlay: Boolean,
    val category: String,
    val collected: Boolean,
    val consumption: Consumption,
    val cover: Cover,
    val dataType: String,
    val date: Long,
    val description: String,
    val text: String,
    val descriptionEditor: String,
    val duration: Int,
    val header: Header,
    val id: Int,
    val idx: Int,
    val ifLimitVideo: Boolean,
    val image: String,
    val library: String,
    val playInfo: List<PlayInfo>,
    val playUrl: String,
    val played: Boolean,
    val provider: Provider,
    val reallyCollected: Boolean,
    val releaseTime: Long,
    val resourceType: String,
    val searchWeight: Int,
    val shade: Boolean,
    val tags: List<Tag>,
    val title: String,
    val type: String,
    val webUrl: WebUrl,
    val itemList: List<Item>
)

data class Header(
    val actionUrl: String,
    val description: String,
    val expert: Boolean,
    val icon: String,
    val iconType: String,
    val id: Int,
    val ifPgc: Boolean,
    val ifShowNotificationIcon: Boolean,
    val title: String,
    val uid: Int
)

data class Author(
    val approvedNotReadyVideoCount: Int,
    val description: String,
    val expert: Boolean,
    val follow: Follow,
    val icon: String,
    val id: Int,
    val ifPgc: Boolean,
    val latestReleaseTime: Long,
    val link: String,
    val name: String,
    val recSort: Int,
    val shield: Shield,
    val videoNum: Int
)

data class Consumption(
    val collectionCount: Int,
    val realCollectionCount: Int,
    val replyCount: Int,
    val shareCount: Int
)

data class Cover(
    val blurred: String,
    val detail: String,
    val feed: String,
    val homepage: String,
)

data class PlayInfo(
    val height: Int,
    val name: String,
    val type: String,
    val url: String,
    val urlList: List<Url>,
    val width: Int
)

data class Provider(
    val alias: String,
    val icon: String,
    val name: String
)

data class Tag(
    val actionUrl: String,
    val bgPicture: String,
    val communityIndex: Int,
    val desc: String,
    val haveReward: Boolean,
    val headerImage: String,
    val id: Int,
    val ifNewest: Boolean,
    val name: String,
    val tagRecType: String
)

data class WebUrl(
    val forWeibo: String,
    val raw: String
)

data class Follow(
    val followed: Boolean,
    val itemId: Int,
    val itemType: String
)

data class Shield(
    val itemId: Int,
    val itemType: String,
    val shielded: Boolean
)

data class Url(
    val name: String,
    val url: String
)