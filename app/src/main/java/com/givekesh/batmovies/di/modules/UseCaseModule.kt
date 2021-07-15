package com.givekesh.batmovies.di.modules

import com.givekesh.batmovies.data.source.repository.MainRepository
import com.givekesh.batmovies.domain.mapper.details.MovieDetailsMapper
import com.givekesh.batmovies.domain.mapper.details.MovieDetailsResponseMapper
import com.givekesh.batmovies.domain.mapper.movies.MovieMapper
import com.givekesh.batmovies.domain.mapper.movies.MovieResponseMapper
import com.givekesh.batmovies.domain.usecase.MovieDetailsUseCase
import com.givekesh.batmovies.domain.usecase.PagingUseCase
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
    fun providePagingUseCase(
        mainRepository: MainRepository,
        responseMapper: MovieResponseMapper,
        mapper: MovieMapper
    ) = PagingUseCase(mainRepository, responseMapper, mapper)

    @Singleton
    @Provides
    fun provideMovieDetailsUseCase(
        mainRepository: MainRepository,
        responseMapper: MovieDetailsResponseMapper,
        mapper: MovieDetailsMapper
    ) = MovieDetailsUseCase(mainRepository, responseMapper, mapper)
}