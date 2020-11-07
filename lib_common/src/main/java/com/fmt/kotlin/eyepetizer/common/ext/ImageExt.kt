package com.fmt.kotlin.eyepetizer.common.ext

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["url", "resourceId", "borderRadius", "isCircle"], requireAll = false)
fun AppCompatImageView.loadUrl(
    url: String?,
    resourceId: Drawable?,
    borderRadius: Int,
    isCircle: Boolean = false
) {

    var requestOptions = RequestOptions()
    if (borderRadius > 0) {
        requestOptions =
            RequestOptions.bitmapTransform(
                RoundedCorners(dp2px(borderRadius.toFloat()))
            )
    }

    if (isCircle) {
        requestOptions = RequestOptions.bitmapTransform(CircleCrop())
    }

    Glide.with(context)
        .run {
            if (url == null) {
                load(resourceId)
            } else {
                load(url)
            }
        }
        .apply(requestOptions)
        .into(this)
}