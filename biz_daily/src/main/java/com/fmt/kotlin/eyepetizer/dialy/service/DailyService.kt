package com.fmt.kotlin.eyepetizer.dialy.service

import com.fmt.kotlin.eyepetizer.common.http.RetrofitClient
import com.fmt.kotlin.eyepetizer.dialy.api.DailyApi

object DailyService : DailyApi by RetrofitClient.instance.create(DailyApi::class.java)
