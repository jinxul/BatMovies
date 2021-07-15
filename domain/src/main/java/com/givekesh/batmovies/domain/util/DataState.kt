package com.givekesh.batmovies.domain.util

sealed class DataState<out R> {
    object Idle : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    class Success<T>(val data: T) : DataState<T>()
    class Failed(val error: Error) : DataState<Nothing>()
}