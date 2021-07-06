package com.givekesh.batmovies.data.source.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.givekesh.batmovies.data.entities.movies.Movie
import com.givekesh.batmovies.domain.mapper.movies.CachedMovieMapper
import com.givekesh.batmovies.domain.mapper.movies.MovieMapper
import java.net.UnknownHostException

class MovieListPagingSource(
    private val mainRepository: MainRepository,
    private val mapper: MovieMapper,
    private val cachedMovieMapper: CachedMovieMapper
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
            val mappedResponse = cachedMovieMapper.mapFromEntityList(data)
            mainRepository.insertCachedMovieList(mappedResponse)
            LoadResult.Page(
                data = data,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            return if (exception is UnknownHostException) {
                val cachedData = fetchCachedData()
                LoadResult.Page(
                    data = cachedData,
                    prevKey = null,
                    nextKey = null
                )
            } else
                LoadResult.Error(exception)
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