package com.givekesh.batmovies.di.modules

import com.givekesh.batmovies.data.source.local.MovieDetailsDao
import com.givekesh.batmovies.data.source.local.MoviesDao
import com.givekesh.batmovies.data.source.remote.NetworkApi
import com.givekesh.batmovies.data.source.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        networkApi: NetworkApi,
        moviesDao: MoviesDao,
        movieDetailsDao: MovieDetailsDao
    ) = MainRepository(networkApi, moviesDao, movieDetailsDao)
}