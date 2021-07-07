package com.givekesh.batmovies.di.components

import com.givekesh.batmovies.di.scopes.BindingAdapterScope
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@BindingAdapterScope
@DefineComponent(parent = SingletonComponent::class)
interface BindingAdapterComponent

@DefineComponent.Builder
interface BindingAdapterComponentBuilder {
    fun build(): BindingAdapterComponent
}