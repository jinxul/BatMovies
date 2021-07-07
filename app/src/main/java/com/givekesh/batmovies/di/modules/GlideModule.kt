package com.givekesh.batmovies.di.modules

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.givekesh.batmovies.R
import com.givekesh.batmovies.di.components.BindingAdapterComponent
import com.givekesh.batmovies.di.scopes.BindingAdapterScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(BindingAdapterComponent::class)
object GlideModule {
    @BindingAdapterScope
    @Provides
    fun provideDiskCacheStrategy(): DiskCacheStrategy = DiskCacheStrategy.AUTOMATIC

    @BindingAdapterScope
    @Provides
    fun provideRequestOptionsFactory(
        strategy: DiskCacheStrategy
    ): Glide.RequestOptionsFactory = Glide.RequestOptionsFactory {
        RequestOptions().diskCacheStrategy(strategy)
            .placeholder(R.drawable.logo)
            .error(R.drawable.ic_broken_image)
    }

    @BindingAdapterScope
    @Provides
    fun provideRequestOptions(
        factory: Glide.RequestOptionsFactory
    ): RequestOptions = factory.build()

    @BindingAdapterScope
    @Provides
    fun provideGlide(
        @ApplicationContext context: Context,
        requestOptions: RequestOptions
    ): RequestManager = Glide.with(context)
        .applyDefaultRequestOptions(requestOptions)
}