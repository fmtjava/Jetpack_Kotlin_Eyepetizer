package com.fmt.kotlin.eyepetizer.common.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * 封装字体图标组件(代替png,有效的减少Apk的体积并且自动适配不会有失真)
 */
class IconFontTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        //设置指定的字体
        val typeface = Typeface.createFromAsset(context.assets, "iconfont.ttf")
        setTypeface(typeface)
    }
}