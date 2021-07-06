package com.givekesh.batmovies.util

sealed class MovieIntent {
    object GetInitialData : MovieIntent()
    class GetMovieDetails(val id: String) : MovieIntent()
}