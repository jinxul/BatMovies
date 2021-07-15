package com.givekesh.batmovies.presentation.di.entrypoints

import androidx.databinding.DataBindingComponent
import com.givekesh.batmovies.presentation.di.components.BindingAdapterComponent
import com.givekesh.batmovies.presentation.di.scopes.BindingAdapterScope
import com.givekesh.batmovies.presentation.util.BindingAdapter
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@BindingAdapterScope
@InstallIn(BindingAdapterComponent::class)
interface BindingAdapterEntryPoint : DataBindingComponent {
    override fun getBindingAdapter(): BindingAdapter
}