package com.givekesh.batmovies.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val poster: String,
    val title: String,
    val type: String,
    val year: String,
    val imdbId: String
) : Parcelable