package com.givekesh.batmovies.domain.usecase

import com.givekesh.batmovies.data.entities.MovieDetails
import com.givekesh.batmovies.data.source.repository.MainRepository
import com.givekesh.batmovies.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(id: String): Flow<DataState<MovieDetails>> = flow {
        emit(DataState.Loading)
        try {
            val response = mainRepository.fetchMovieDetails(id)
            emit(DataState.Success(response))
        } catch (e: Exception) {
            emit(DataState.Failed(e))
        }
    }
}