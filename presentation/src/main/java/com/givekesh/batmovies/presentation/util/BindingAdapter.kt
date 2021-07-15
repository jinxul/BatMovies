package com.givekesh.batmovies.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager
import com.givekesh.batmovies.presentation.di.scopes.BindingAdapterScope
import javax.inject.Inject

@BindingAdapterScope
class BindingAdapter @Inject constructor(
    private val glide: RequestManager
) {
    @BindingAdapter("loadWithGlide")
    fun loadWithGlide(view: ImageView, url: String) {
        glide.load(url).into(view)
    }
}