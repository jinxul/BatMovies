package com.givekesh.batmovies.domain.mapper.movies

import com.givekesh.batmovies.data.entities.movies.CachedMovie
import com.givekesh.batmovies.data.entities.movies.Movie
import com.givekesh.batmovies.util.EntityMapper
import javax.inject.Inject

class CachedMovieMapper @Inject constructor() : EntityMapper<Movie, CachedMovie> {
    override fun mapFromEntity(
        entity: Movie
    ): CachedMovie = CachedMovie(
        poster = entity.poster,
        title = entity.title,
        type = entity.type,
        year = entity.year,
        imdbId = entity.imdbId
    )

    override fun mapFromEntityList(
        entities: List<Movie>
    ): List<CachedMovie> = entities.map { mapFromEntity(it) }
}