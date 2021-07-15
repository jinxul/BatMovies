package com.givekesh.batmovies.data.util

import com.givekesh.batmovies.data.entities.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

class Error(private val exception: Exception) {
    fun errorMessage(): String = when (exception) {
        is IOException -> "No internet connection"
        is NullPointerException -> "offline data is not available"
        is HttpException -> convertErrorBody(exception)
        else -> "Unexpected Error"
    }

    private fun convertErrorBody(exception: HttpException) =
        exception.response()?.errorBody()?.string()?.let {
            Gson().fromJson(it, ErrorResponse::class.java)
        }?.error ?: "Unexpected Error"
}