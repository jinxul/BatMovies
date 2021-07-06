package com.givekesh.batmovies.domain.usecase

import com.givekesh.batmovies.data.entities.details.MovieDetails
import com.givekesh.batmovies.data.source.repository.MainRepository
import com.givekesh.batmovies.domain.mapper.details.CachedMovieDetailsMapper
import com.givekesh.batmovies.domain.mapper.details.MovieDetailsMapper
import com.givekesh.batmovies.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val mapper: MovieDetailsMapper,
    private val cachedMapper: CachedMovieDetailsMapper
) {
    suspend operator fun invoke(id: String): Flow<DataState<MovieDetails>> = flow {
        emit(DataState.Loading)
        try {
            val response = mainRepository.fetchMovieDetails(id)
            val mappedResponse = cachedMapper.mapFromEntity(response)
            mainRepository.insertCachedMovieDetails(mappedResponse)
            emit(DataState.Success(response))
        } catch (e: Exception) {
            if (e is UnknownHostException)
                fetchCachedData(id).collect { emit(it) }
            else
                emit(DataState.Failed(e))
        }
    }

    private suspend fun fetchCachedData(id: String): Flow<DataState<MovieDetails>> = flow {
        val response = mainRepository.fetchCachedMovieDetails(id)
        val mapped = mapper.mapFromEntity(response)
        emit(DataState.Success(mapped))
    }
}