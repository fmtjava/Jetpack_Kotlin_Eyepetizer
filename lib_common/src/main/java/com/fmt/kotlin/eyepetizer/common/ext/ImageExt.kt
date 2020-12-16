package com.fmt.kotlin.eyepetizer.common.ext

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load

@BindingAdapter(
    value = ["url", "showLoading", "strokeWidth", "centerRadius", "allowHardware"],
    requireAll = false
)
fun ImageView.loadUrl(
    url: String,
    showLoading: Boolean = false,
    strokeWidth: Float = dp2px(5f).toFloat(),
    centerRadius: Float = dp2px(30f).toFloat(),
    allowHardware: Boolean = true
) {
    if (url.isNotEmpty()) {
        load(url) {
            allowHardware(allowHardware)
            //设置网络图片加载动画，体验更好
            if (showLoading) {
                val circularProgressDrawable = CircularProgressDrawable(context)
                circularProgressDrawable.strokeWidth = strokeWidth
                circularProgressDrawable.centerRadius = centerRadius
                circularProgressDrawable.setColorSchemeColors(Color.RED)
                circularProgressDrawable.start()
                placeholder(circularProgressDrawable)
            }
        }
    }
}