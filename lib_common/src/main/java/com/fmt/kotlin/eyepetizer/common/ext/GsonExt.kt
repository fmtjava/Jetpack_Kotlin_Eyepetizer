package com.fmt.kotlin.eyepetizer.common.ext

import com.google.gson.Gson
import java.lang.reflect.Type

val gson = Gson()

fun toJson(any: Any): String = gson.toJson(any)

fun <T> fromJson(json: String, typeOfT: Type): T = gson.fromJson(json, typeOfT)

inline fun <reified T> fromJson(json: String): T = gson.fromJson(json, T::class.java)
