package com.givekesh.batmovies.data.entities.details

import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName("Source")
    val source: String,
    @SerializedName("Value")
    val value: String
)