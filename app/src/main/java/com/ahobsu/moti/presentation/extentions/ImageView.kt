package com.ahobsu.moti.presentation.extentions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .into(this)
}