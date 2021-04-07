package com.fmt.kotlin.eyepetizer.common.ext

import com.tencent.mmkv.MMKV
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

val mmkv = MMKV.defaultMMKV()

class MMKVExt<T>(private val key: String, private val defaultValue: T) :
    ReadWriteProperty<Any, T?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T? = find(key, defaultValue)

    private fun find(key: String, defaultValue: T): T? =
        when (defaultValue) {
            is Int -> mmkv.decodeInt(key)
            is Boolean -> mmkv.decodeBool(key)
            is Double -> mmkv.decodeDouble(key)
            is Float -> mmkv.decodeFloat(key)
            is Long -> mmkv.decodeLong(key)
            is String -> mmkv.decodeString(key)
            else -> throw IllegalArgumentException("This type can't be saved into MMKV")
        } as T

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        with(mmkv) {
            when (value) {
                is Int -> encode(key, value)
                is Boolean -> encode(key, value)
                is Double -> encode(key, value)
                is Float -> encode(key, value)
                is Long -> encode(key, value)
                is String -> encode(key, value)
                else -> throw IllegalArgumentException("This type can't be saved into MMKV")
            }
        }
    }
}