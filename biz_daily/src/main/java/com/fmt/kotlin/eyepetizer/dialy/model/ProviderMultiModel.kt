package com.fmt.kotlin.eyepetizer.dialy.model

import androidx.annotation.IntDef
import com.fmt.kotlin.eyepetizer.provider.model.Item

class ProviderMultiModel(
    @Type val type: Int,
    val item: Item? = null,
    val items: List<Item> = mutableListOf()
) {

    @IntDef(value = [Type.TYPE_BANNER, Type.TYPE_TITLE, Type.TYPE_IMAGE])
    @Target(AnnotationTarget.VALUE_PARAMETER)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Type {
        companion object {
            const val TYPE_BANNER = 0
            const val TYPE_TITLE = 1
            const val TYPE_IMAGE = 2
        }
    }


}