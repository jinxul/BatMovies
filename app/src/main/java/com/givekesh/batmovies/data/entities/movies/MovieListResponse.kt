package com.givekesh.batmovies.data.entities.movies

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val data: List<MovieResponse>,
    @SerializedName("totalResults")
    val totalResults: String
)