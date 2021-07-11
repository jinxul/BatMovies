package com.givekesh.batmovies.data.entities.details

import androidx.room.*

@Entity(tableName = "MovieDetails")
data class CachedMovieDetails(
    @ColumnInfo(name = "actors")
    val actors: String,
    @ColumnInfo(name = "awards")
    val awards: String,
    @ColumnInfo(name = "boxOffice")
    val boxOffice: String,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "dvd")
    val dvd: String,
    @ColumnInfo(name = "director")
    val director: String,
    @ColumnInfo(name = "genre")
    val genre: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "imdbId")
    val imdbId: String,
    @ColumnInfo(name = "imdbRating")
    val imdbRating: String,
    @ColumnInfo(name = "imdbVotes")
    val imdbVotes: String,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "metaScore")
    val metaScore: String,
    @ColumnInfo(name = "plot")
    val plot: String,
    @ColumnInfo(name = "poster")
    val poster: String,
    @ColumnInfo(name = "production")
    val production: String,
    @ColumnInfo(name = "rated")
    val rated: String,
    @ColumnInfo(name = "released")
    val released: String,
    @ColumnInfo(name = "response")
    val response: String,
    @ColumnInfo(name = "runtime")
    val runtime: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "website")
    val website: String,
    @ColumnInfo(name = "writer")
    val writer: String,
    @ColumnInfo(name = "year")
    val year: String
)

@Entity(indices = [Index(value = ["movieId", "source"], unique = true)])
data class CachedRatings(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val movieId: String,
    val source: String,
    val value: String
)

data class CachedMovieDetailsWithRatings(
    @Embedded val details: CachedMovieDetails,
    @Relation(
        parentColumn = "imdbId",
        entityColumn = "movieId"
    )
    val ratings: List<CachedRatings>
)