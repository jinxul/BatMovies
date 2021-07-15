package com.givekesh.batmovies.presentation.util

sealed class MovieIntent {
    object GetInitialData : MovieIntent()
    class GetMovieDetails(val id: String) : MovieIntent()
}