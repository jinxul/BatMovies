package com.givekesh.batmovies.data.entities.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
data class CachedMovie(
    @ColumnInfo(name = "poster")
    val poster: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "year")
    val year: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "imdbId")
    val imdbId: String
)