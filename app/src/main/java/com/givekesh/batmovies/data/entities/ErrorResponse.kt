package com.givekesh.batmovies.data.entities

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("Error")
    val error: String,
    @SerializedName("Response")
    val response: String
)