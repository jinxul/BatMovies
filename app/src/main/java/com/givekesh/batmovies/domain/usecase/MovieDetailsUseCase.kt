package com.givekesh.batmovies.domain.usecase

import com.givekesh.batmovies.data.source.repository.MainRepository
import com.givekesh.batmovies.domain.entities.MovieDetails
import com.givekesh.batmovies.domain.mapper.details.MovieDetailsMapper
import com.givekesh.batmovies.domain.mapper.details.MovieDetailsResponseMapper
import com.givekesh.batmovies.util.DataState
import com.givekesh.batmovies.util.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val responseMapper: MovieDetailsResponseMapper,
    private val mapper: MovieDetailsMapper
) {
    suspend operator fun invoke(id: String): Flow<DataState<MovieDetails>> = flow {
        emit(DataState.Loading)
        try {
            val response = mainRepository.fetchMovieDetails(id)
            val cachedEntity = responseMapper.mapFromEntity(response)
            val mappedResponse = mapper.mapFromEntity(cachedEntity)
            mainRepository.insertCachedMovieDetails(cachedEntity)
            emit(DataState.Success(mappedResponse))
        } catch (e: Exception) {
            if (e is UnknownHostException)
                fetchCachedData(id).collect { emit(it) }
            else
                emit(DataState.Failed(Error(e)))
        }
    }

    private suspend fun fetchCachedData(id: String): Flow<DataState<MovieDetails>> = flow {
        try {
            val response = mainRepository.fetchCachedMovieDetails(id)
            val mapped = mapper.mapFromEntity(response)
            emit(DataState.Success(mapped))
        } catch (e: Exception) {
            emit(DataState.Failed(Error(e)))
        }
    }
}