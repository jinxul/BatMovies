package com.givekesh.batmovies.presentation.movie_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.givekesh.batmovies.data.entities.details.MovieDetails
import com.givekesh.batmovies.data.entities.movies.Movie
import com.givekesh.batmovies.domain.usecase.MovieDetailsUseCase
import com.givekesh.batmovies.domain.usecase.PagerUseCase
import com.givekesh.batmovies.util.DataState
import com.givekesh.batmovies.util.MovieIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val pagerUseCase: PagerUseCase,
    private val movieDetailsUseCase: MovieDetailsUseCase
) : ViewModel() {
    val channel = Channel<MovieIntent>()

    private val _movieDetailsDataState = MutableStateFlow<DataState<MovieDetails>>(DataState.Idle)
    val movieDetailsDataState: StateFlow<DataState<MovieDetails>> get() = _movieDetailsDataState

    var moviePager: Flow<PagingData<Movie>> = flowOf()

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { movieIntent ->
                when (movieIntent) {
                    MovieIntent.GetInitialData -> moviePager =
                        pagerUseCase().cachedIn(viewModelScope)
                    is MovieIntent.GetMovieDetails -> movieDetailsUseCase(id = movieIntent.id)
                        .onEach { _movieDetailsDataState.value = it }
                        .launchIn(viewModelScope)
                }
            }
        }
    }
}