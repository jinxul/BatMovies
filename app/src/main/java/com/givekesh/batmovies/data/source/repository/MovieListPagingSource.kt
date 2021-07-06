package com.givekesh.batmovies.data.source.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.givekesh.batmovies.data.entities.movies.Movie
import retrofit2.HttpException
import java.io.IOException

class MovieListPagingSource(
    private val mainRepository: MainRepository
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
            LoadResult.Page(
                data = data,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}