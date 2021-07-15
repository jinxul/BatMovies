package com.givekesh.batmovies.data.entities.movies

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("imdbID")
    val imdbId: String
)