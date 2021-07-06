package com.givekesh.batmovies.di.modules

import com.givekesh.batmovies.data.source.repository.MainRepository
import com.givekesh.batmovies.domain.mapper.details.CachedMovieDetailsMapper
import com.givekesh.batmovies.domain.mapper.movies.CachedMovieMapper
import com.givekesh.batmovies.domain.mapper.details.MovieDetailsMapper
import com.givekesh.batmovies.domain.mapper.movies.MovieMapper
import com.givekesh.batmovies.domain.usecase.MovieDetailsUseCase
import com.givekesh.batmovies.domain.usecase.PagerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun providePagerUseCase(
        mainRepository: MainRepository,
        mapper: MovieMapper,
        cachedMovieMapper: CachedMovieMapper
    ) = PagerUseCase(mainRepository, mapper, cachedMovieMapper)

    @Singleton
    @Provides
    fun provideMovieDetailsUseCase(
        mainRepository: MainRepository,
        mapper: MovieDetailsMapper,
        cachedMapper: CachedMovieDetailsMapper
    ) = MovieDetailsUseCase(mainRepository, mapper, cachedMapper)
}