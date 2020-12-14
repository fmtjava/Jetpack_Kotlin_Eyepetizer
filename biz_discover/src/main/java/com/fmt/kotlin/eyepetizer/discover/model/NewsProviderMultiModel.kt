package com.fmt.kotlin.eyepetizer.discover.model

import androidx.annotation.IntDef

class NewsProviderMultiModel(@Type val type: Int, val newsItemModel: NewsItemModel) {

    @IntDef(value = [Type.TYPE_TITLE, Type.TYPE_CONTENT])
    annotation class Type {
        companion object {
            const val TYPE_TITLE = 0
            const val TYPE_CONTENT = 1
        }
    }
}