package com.givekesh.batmovies.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import javax.inject.Inject

class BindingAdapter @Inject constructor() {
    @BindingAdapter("loadWithGlide")
    fun loadWithGlide(view: ImageView, url: String) {
        Glide.with(view)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(view)
    }
}