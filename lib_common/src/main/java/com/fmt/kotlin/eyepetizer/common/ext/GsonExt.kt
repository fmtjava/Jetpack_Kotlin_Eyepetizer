package com.fmt.kotlin.eyepetizer.common.ext

import com.google.gson.Gson

val gson = Gson()

fun toJson(any: Any): String = gson.toJson(any)

inline fun <reified T> fromJson(json: String): T = gson.fromJson<T>(json, T::class.java)