package com.givekesh.batmovies.data.source.remote

import com.givekesh.batmovies.data.entities.details.MovieDetailsResponse
import com.givekesh.batmovies.data.entities.movies.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NetworkApi {
    @GET(".")
    suspend fun fetchMovieList(@QueryMap map: Map<String, String>): MovieListResponse

    @GET(".")
    suspend fun fetchMovieDetails(@QueryMap map: Map<String, String>): MovieDetailsResponse
}