package com.skg.mybook.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("app:loadImage")
fun loadImage(view: ImageView, imgUrl: String?) {
    Glide.with(view.context).load(imgUrl).centerCrop().into(view)
}