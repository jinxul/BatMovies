package com.givekesh.batmovies.di.entrypoints

import androidx.databinding.DataBindingComponent
import com.givekesh.batmovies.di.components.BindingAdapterComponent
import com.givekesh.batmovies.di.scopes.BindingAdapterScope
import com.givekesh.batmovies.util.BindingAdapter
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@BindingAdapterScope
@InstallIn(BindingAdapterComponent::class)
interface BindingAdapterEntryPoint : DataBindingComponent {
    override fun getBindingAdapter(): BindingAdapter
}