package com.givekesh.batmovies.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.givekesh.batmovies.data.source.repository.MainRepository
import com.givekesh.batmovies.data.source.repository.MovieListPagingSource
import javax.inject.Inject

class PagerUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    operator fun invoke() = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { MovieListPagingSource(mainRepository) }
    ).flow
}