package com.fmt.kotlin.eyepetizer.dialy.model

import androidx.annotation.IntDef

class ProviderMultiModel(
    @Type val type: Int,
    val item: DailyItem? = null,
    val items: List<DailyItem> = mutableListOf()
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