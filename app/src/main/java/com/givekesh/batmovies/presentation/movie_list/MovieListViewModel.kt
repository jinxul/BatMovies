package com.givekesh.batmovies.presentation.movie_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.givekesh.batmovies.domain.entities.Movie
import com.givekesh.batmovies.domain.entities.MovieDetails
import com.givekesh.batmovies.domain.usecase.MovieDetailsUseCase
import com.givekesh.batmovies.domain.usecase.PagingUseCase
import com.givekesh.batmovies.domain.util.DataState
import com.givekesh.batmovies.util.MovieIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val pagingUseCase: PagingUseCase
) : ViewModel() {
    val channel = Channel<MovieIntent>()

    private val _movieDetailsDataState = MutableStateFlow<DataState<MovieDetails>>(DataState.Idle)
    val movieDetailsResponseDataState: StateFlow<DataState<MovieDetails>> get() = _movieDetailsDataState

    var movieResponsePager: Flow<PagingData<Movie>> = flowOf()

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { movieIntent ->
                when (movieIntent) {
                    MovieIntent.GetInitialData -> movieResponsePager = getPager()
                    is MovieIntent.GetMovieDetails -> movieDetailsUseCase(id = movieIntent.id)
                        .onEach { _movieDetailsDataState.value = it }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    private fun getPager() = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { pagingUseCase }
    ).flow.cachedIn(viewModelScope)
}