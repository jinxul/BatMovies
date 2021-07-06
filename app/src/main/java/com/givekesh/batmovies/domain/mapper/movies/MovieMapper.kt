package com.givekesh.batmovies.domain.mapper.movies

import com.givekesh.batmovies.data.entities.movies.CachedMovie
import com.givekesh.batmovies.data.entities.movies.Movie
import com.givekesh.batmovies.util.EntityMapper
import javax.inject.Inject

class MovieMapper @Inject constructor() : EntityMapper<CachedMovie, Movie> {
    override fun mapFromEntity(
        entity: CachedMovie
    ): Movie = Movie(
        poster = entity.poster,
        title = entity.title,
        type = entity.type,
        year = entity.year,
        imdbId = entity.imdbId
    )

    override fun mapFromEntityList(
        entities: List<CachedMovie>
    ): List<Movie> = entities.map { mapFromEntity(it) }
}