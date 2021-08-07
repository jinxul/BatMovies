package com.givekesh.batmovies.domain.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.givekesh.batmovies.data.source.repository.MainRepository
import com.givekesh.batmovies.data.util.ErrorEvent
import com.givekesh.batmovies.domain.entities.Movie
import com.givekesh.batmovies.domain.mapper.movies.MovieMapper
import com.givekesh.batmovies.domain.mapper.movies.MovieResponseMapper
import java.net.UnknownHostException
import javax.inject.Inject

class PagingUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val responseMapper: MovieResponseMapper,
    private val mapper: MovieMapper
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: 1
        return try {
            val response = mainRepository.fetchMovieList(position)
            val data = response.data
            val nextKey = if (data.isEmpty()) {
                null
            } else {
                position + 1
            }
            val cachedEntity = responseMapper.mapFromEntityList(data)
            mainRepository.insertCachedMovieList(cachedEntity)
            val mappedResponse = mapper.mapFromEntityList(cachedEntity)
            LoadResult.Page(
                data = mappedResponse,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            return if (exception is UnknownHostException) {
                val cachedData = fetchCachedData()
                if(cachedData.isNotEmpty()) {
                    LoadResult.Page(
                        data = cachedData,
                        prevKey = null,
                        nextKey = null
                    )
                } else {
                    LoadResult.Error(ErrorEvent(exception))
                }
            } else
                LoadResult.Error(ErrorEvent(exception))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private suspend fun fetchCachedData(): List<Movie> {
        val response = mainRepository.fetchCachedMovieList()
        return mapper.mapFromEntityList(response)
    }
}