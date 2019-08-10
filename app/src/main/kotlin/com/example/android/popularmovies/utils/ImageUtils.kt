package com.example.android.popularmovies.utils

import androidx.databinding.BindingAdapter
import android.widget.ImageView

import com.bumptech.glide.Glide

object ImageUtils {

    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}
