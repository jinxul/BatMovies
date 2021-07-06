package com.givekesh.batmovies.domain.mapper.details

import com.givekesh.batmovies.data.entities.details.CachedMovieDetails
import com.givekesh.batmovies.data.entities.details.MovieDetails
import com.givekesh.batmovies.util.EntityMapper
import javax.inject.Inject

class CachedMovieDetailsMapper @Inject constructor() : EntityMapper<MovieDetails, CachedMovieDetails> {
    override fun mapFromEntity(
        entity: MovieDetails
    ): CachedMovieDetails = CachedMovieDetails(
        actors = entity.actors,
        awards = entity.awards,
        boxOffice = entity.boxOffice,
        country = entity.country,
        dvd = entity.dvd,
        director = entity.director,
        genre = entity.genre,
        imdbId = entity.imdbId,
        imdbRating = entity.imdbRating,
        imdbVotes = entity.imdbVotes,
        language = entity.language,
        metaScore = entity.metaScore,
        plot = entity.plot,
        poster = entity.poster,
        production = entity.production,
        rated = entity.rated,
        released = entity.released,
        response = entity.response,
        runtime = entity.runtime,
        title = entity.title,
        type = entity.type,
        website = entity.website,
        writer = entity.writer,
        year = entity.year
    )

    override fun mapFromEntityList(
        entities: List<MovieDetails>
    ): List<CachedMovieDetails> = entities.map { mapFromEntity(it) }
}