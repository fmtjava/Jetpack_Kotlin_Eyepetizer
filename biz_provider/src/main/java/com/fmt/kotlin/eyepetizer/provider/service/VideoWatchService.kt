package com.fmt.kotlin.eyepetizer.provider.service

import com.alibaba.android.arouter.facade.template.IProvider
import com.fmt.kotlin.eyepetizer.provider.model.Data

interface VideoWatchService : IProvider {

    fun getVideoWatchList(): MutableList<Data>

    fun removeVideoWatchRecord(data: Data)

}