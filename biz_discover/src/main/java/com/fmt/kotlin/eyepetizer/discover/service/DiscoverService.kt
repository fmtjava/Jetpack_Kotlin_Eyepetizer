package com.fmt.kotlin.eyepetizer.discover.service

import com.fmt.kotlin.eyepetizer.common.http.RetrofitClient
import com.fmt.kotlin.eyepetizer.discover.api.DiscoverApi

object DiscoverService : DiscoverApi by RetrofitClient.instance.create(DiscoverApi::class.java)