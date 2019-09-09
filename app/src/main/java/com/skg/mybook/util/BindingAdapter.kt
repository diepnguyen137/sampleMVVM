package com.skg.mybook.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:loadImage")
fun loadImage(view: ImageView, imgUrl: String?) {
    Glide.with(view.context).load(imgUrl).centerCrop().into(view)
}

@BindingAdapter("app:enableSave")
fun enableSave(view: ImageView, fromHome: Boolean){
    if(!fromHome){
        view.visibility = View.GONE
    }
}
@BindingAdapter("app:enableLoading")
fun enableLoading(view: ProgressBar, isLoading: Boolean){
    if(!isLoading){
        view.visibility = View.GONE
    }
}