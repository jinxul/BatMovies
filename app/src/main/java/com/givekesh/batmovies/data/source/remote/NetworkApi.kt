package com.givekesh.batmovies.data.source.remote

import com.givekesh.batmovies.data.entities.MovieDetails
import com.givekesh.batmovies.data.entities.MovieList
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NetworkApi {
    @GET(".")
    suspend fun fetchMovieList(@QueryMap map: Map<String, String>): MovieList

    @GET(".")
    suspend fun fetchMovieDetails(@QueryMap map: Map<String, String>): MovieDetails
}