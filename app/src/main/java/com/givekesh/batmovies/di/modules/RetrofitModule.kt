package com.givekesh.batmovies.di.modules

import com.givekesh.batmovies.data.source.remote.NetworkApi
import com.givekesh.batmovies.util.Constant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideGsonConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideRetrofit(factory: GsonConverterFactory): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(factory)

    @Singleton
    @Provides
    fun provideNetworkApi(retrofit: Retrofit.Builder): NetworkApi = retrofit.build()
        .create(NetworkApi::class.java)
}