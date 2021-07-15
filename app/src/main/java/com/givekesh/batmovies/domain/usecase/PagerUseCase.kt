package com.givekesh.batmovies.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.givekesh.batmovies.data.source.repository.MainRepository
import com.givekesh.batmovies.data.source.repository.MovieListPagingSource
import com.givekesh.batmovies.domain.mapper.movies.MovieMapper
import com.givekesh.batmovies.domain.mapper.movies.MovieResponseMapper
import javax.inject.Inject

class PagerUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val responseMapper: MovieResponseMapper,
    private val mapper: MovieMapper
) {
    operator fun invoke() = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { MovieListPagingSource(mainRepository, responseMapper, mapper) }
    ).flow
}