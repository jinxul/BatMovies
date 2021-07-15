package com.givekesh.batmovies.domain.mapper.movies

import com.givekesh.batmovies.data.entities.movies.CachedMovie
import com.givekesh.batmovies.domain.entities.Movie
import com.givekesh.batmovies.domain.util.EntityListMapper
import javax.inject.Inject

class MovieMapper @Inject constructor() : EntityListMapper<CachedMovie, Movie> {
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

    override fun mapToEntity(model: Movie): CachedMovie = CachedMovie(
        poster = model.poster,
        title = model.title,
        type = model.type,
        year = model.year,
        imdbId = model.imdbId
    )

    override fun mapToEntityList(models: List<Movie>): List<CachedMovie> =
        models.map { mapToEntity(it) }
}