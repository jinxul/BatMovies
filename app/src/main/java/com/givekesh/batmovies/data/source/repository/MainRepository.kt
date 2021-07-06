package com.givekesh.batmovies.data.source.repository

import com.givekesh.batmovies.data.entities.details.CachedMovieDetails
import com.givekesh.batmovies.data.entities.details.MovieDetails
import com.givekesh.batmovies.data.entities.movies.CachedMovie
import com.givekesh.batmovies.data.entities.movies.MovieList
import com.givekesh.batmovies.data.source.local.MovieDetailsDao
import com.givekesh.batmovies.data.source.local.MoviesDao
import com.givekesh.batmovies.data.source.remote.NetworkApi
import com.givekesh.batmovies.util.Constant
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val networkApi: NetworkApi,
    private val moviesDao: MoviesDao,
    private val movieDetailsDao: MovieDetailsDao
) {
    suspend fun fetchMovieList(page: Int): MovieList {
        val options = mapOf(
            "apikey" to Constant.apiKey,
            "s" to "batman",
            "type" to "movie",
            "page" to "$page"
        )
        return networkApi.fetchMovieList(options)
    }

    suspend fun fetchCachedMovieList(): List<CachedMovie> = moviesDao.getAll()

    suspend fun insertCachedMovieList(data: List<CachedMovie>) {
        moviesDao.insert(data)
    }

    suspend fun fetchMovieDetails(id: String): MovieDetails {
        val options = mapOf(
            "apikey" to Constant.apiKey,
            "i" to id
        )
        return networkApi.fetchMovieDetails(options)
    }

    suspend fun fetchCachedMovieDetails(id: String): CachedMovieDetails = movieDetailsDao.get(id)

    suspend fun insertCachedMovieDetails(data: CachedMovieDetails) {
        movieDetailsDao.insert(data)
    }
}