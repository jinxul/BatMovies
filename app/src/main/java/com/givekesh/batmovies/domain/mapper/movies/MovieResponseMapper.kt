package com.givekesh.batmovies.domain.mapper.movies

import com.givekesh.batmovies.data.entities.movies.CachedMovie
import com.givekesh.batmovies.data.entities.movies.MovieResponse
import com.givekesh.batmovies.util.EntityListMapper
import javax.inject.Inject

class MovieResponseMapper @Inject constructor() : EntityListMapper<MovieResponse, CachedMovie> {
    override fun mapFromEntity(entity: MovieResponse): CachedMovie = CachedMovie(
        poster = entity.poster,
        title = entity.title,
        type = entity.type,
        year = entity.year,
        imdbId = entity.imdbId
    )

    override fun mapToEntity(model: CachedMovie): MovieResponse = MovieResponse(
        poster = model.poster,
        title = model.title,
        type = model.type,
        year = model.year,
        imdbId = model.imdbId
    )

    override fun mapFromEntityList(entities: List<MovieResponse>): List<CachedMovie> =
        entities.map { mapFromEntity(it) }

    override fun mapToEntityList(models: List<CachedMovie>): List<MovieResponse> =
        models.map { mapToEntity(it) }
}