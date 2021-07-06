package com.givekesh.batmovies.data.entities

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val search: List<Search>,
    @SerializedName("totalResults")
    val totalResults: String
)