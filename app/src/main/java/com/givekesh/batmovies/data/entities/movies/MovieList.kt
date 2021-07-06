package com.givekesh.batmovies.data.entities.movies

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val data: List<Movie>,
    @SerializedName("totalResults")
    val totalResults: String
)