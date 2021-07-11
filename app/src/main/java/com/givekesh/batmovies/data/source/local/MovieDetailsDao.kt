package com.givekesh.batmovies.data.source.local

import androidx.room.*
import com.givekesh.batmovies.data.entities.details.CachedMovieDetails
import com.givekesh.batmovies.data.entities.details.CachedMovieDetailsWithRatings
import com.givekesh.batmovies.data.entities.details.CachedRatings

@Dao
interface MovieDetailsDao {
    @Transaction
    suspend fun insert(data: CachedMovieDetailsWithRatings) {
        insertDetails(data.details)
        insertRatings(data.ratings)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(details: CachedMovieDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRatings(ratings: List<CachedRatings>)

    @Transaction
    @Query("SELECT * FROM MovieDetails WHERE imdbId = :id")
    suspend fun get(id: String): CachedMovieDetailsWithRatings
}