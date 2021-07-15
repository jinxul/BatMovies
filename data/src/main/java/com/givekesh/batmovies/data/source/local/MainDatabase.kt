package com.givekesh.batmovies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.givekesh.batmovies.data.entities.details.CachedMovieDetails
import com.givekesh.batmovies.data.entities.details.CachedRatings
import com.givekesh.batmovies.data.entities.movies.CachedMovie

@Database(
    entities = [CachedMovie::class, CachedMovieDetails::class, CachedRatings::class],
    version = 2,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun movieDetailsDao(): MovieDetailsDao
}