package com.fmt.kotlin.eyepetizer.hot.model

data class TabInfo(
    val tabInfo: TabInfoX
)

data class TabInfoX(
    val defaultIdx: Int,
    val tabList: List<Tab>
)

data class Tab(
    val adTrack: Any,
    val apiUrl: String,
    val id: Int,
    val name: String,
    val nameType: Int,
    val tabType: Int
)