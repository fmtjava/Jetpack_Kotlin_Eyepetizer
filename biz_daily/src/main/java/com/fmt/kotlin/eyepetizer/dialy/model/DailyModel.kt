package com.fmt.kotlin.eyepetizer.dialy.model

import com.fmt.kotlin.eyepetizer.provider.model.Issue

data class DailyModel(
    val dialog: Any,
    val issueList: List<Issue>,
    val newestIssueType: String,
    val nextPageUrl: String,
    val nextPublishTime: Long
)

