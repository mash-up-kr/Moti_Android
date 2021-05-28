package com.ahobsu.moti.presentation.extentions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.zomato.photofilters.imageprocessors.Filter
import com.zomato.photofilters.imageprocessors.subfilters.ColorOverlaySubFilter
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubFilter
import javax.annotation.Nullable

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .into(this)
}

@BindingAdapter("setCircleImageUrl")
fun ImageView.setCircleImageUrl(url: String) {
    Glide.with(context)
        .load(url)
        .circleCrop()
        .into(this)
}

@BindingAdapter("filterImageUrl")
fun setCircleFilterImageUrl(view: ImageView, url: String?) {
    System.loadLibrary("NativeImageProcessor")
    Glide.with(view.context)
        .asBitmap()
        .load(url)
        .circleCrop()
        .into(object : CustomTarget<Bitmap?>() {
            override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap?>?
            ) {
                val myFilter = Filter()
                myFilter.addSubFilter(SaturationSubFilter(0f))
                myFilter.addSubFilter(ColorOverlaySubFilter(70, .645f, .48f, .32f))
                val outputImage = myFilter.processFilter(resource)
                view.setImageBitmap(outputImage)
            }
        })
}
