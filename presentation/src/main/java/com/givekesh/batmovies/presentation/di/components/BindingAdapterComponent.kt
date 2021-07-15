package com.givekesh.batmovies.presentation.di.components

import com.givekesh.batmovies.presentation.di.scopes.BindingAdapterScope
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@BindingAdapterScope
@DefineComponent(parent = SingletonComponent::class)
interface BindingAdapterComponent

@DefineComponent.Builder
interface BindingAdapterComponentBuilder {
    fun build(): BindingAdapterComponent
}