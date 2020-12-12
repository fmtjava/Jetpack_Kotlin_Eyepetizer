package com.fmt.kotlin.eyepetizer.common.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun formatDateMsByMS(milliseconds: Long): String {
        val simpleDateFormat = SimpleDateFormat("mm:ss")
        return simpleDateFormat.format(Date(milliseconds))
    }

    fun formatDateMsByYMD(milliseconds: Long): String {
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
        return simpleDateFormat.format(Date(milliseconds))
    }

    fun formatDateMsByYMDHM(milliseconds: Long): String {
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm")
        return simpleDateFormat.format(Date(milliseconds))
    }
}