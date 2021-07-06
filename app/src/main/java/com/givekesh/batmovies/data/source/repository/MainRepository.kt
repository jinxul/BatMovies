package com.givekesh.batmovies.data.source.repository

import com.givekesh.batmovies.data.entities.details.MovieDetails
import com.givekesh.batmovies.data.entities.movies.MovieList
import com.givekesh.batmovies.data.source.remote.NetworkApi
import com.givekesh.batmovies.util.Constant
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val networkApi: NetworkApi
) {
    suspend fun fetchMovieList(page: Int): MovieList {
        val options = mapOf(
            "apikey" to Constant.apiKey,
            "s" to "batman",
            "type" to "movie",
            "page" to "$page"
        )
        return networkApi.fetchMovieList(options)
    }

    suspend fun fetchMovieDetails(id: String): MovieDetails {
        val options = mapOf(
            "apikey" to Constant.apiKey,
            "i" to id
        )
        return networkApi.fetchMovieDetails(options)
    }
}