package com.givekesh.batmovies.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.givekesh.batmovies.data.entities.movies.CachedMovie

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<CachedMovie>)

    @Query("SELECT * FROM Movies")
    suspend fun getAll(): List<CachedMovie>
}