package com.fmt.kotlin.eyepetizer.common.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class IconFontTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {


    init {
        val typeface = Typeface.createFromAsset(context.assets, "iconfont.ttf")
        setTypeface(typeface)
    }
}