package com.fmt.kotlin.eyepetizer.hot.service

import com.fmt.kotlin.eyepetizer.common.http.RetrofitClient
import com.fmt.kotlin.eyepetizer.hot.api.HotApi

object HotService : HotApi by RetrofitClient.create(HotApi::class.java)