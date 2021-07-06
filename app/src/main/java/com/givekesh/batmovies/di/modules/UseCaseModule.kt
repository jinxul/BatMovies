package com.givekesh.batmovies.di.modules

import com.givekesh.batmovies.data.source.repository.MainRepository
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
    fun providePagerUseCase(mainRepository: MainRepository) = PagerUseCase(mainRepository)

    @Singleton
    @Provides
    fun provideMovieDetailsUseCase(
        mainRepository: MainRepository
    ) = MovieDetailsUseCase(mainRepository)
}