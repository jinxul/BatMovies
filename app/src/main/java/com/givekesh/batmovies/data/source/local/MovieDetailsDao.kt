package com.givekesh.batmovies.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.givekesh.batmovies.data.entities.details.CachedMovieDetails

@Dao
interface MovieDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cachedMovieDetails: CachedMovieDetails): Long

    @Query("SELECT * FROM MovieDetails WHERE imdbId = :id")
    suspend fun get(id: String): CachedMovieDetails
}