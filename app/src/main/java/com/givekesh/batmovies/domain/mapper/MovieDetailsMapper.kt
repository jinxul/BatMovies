package com.givekesh.batmovies.domain.mapper

import com.givekesh.batmovies.data.entities.details.CachedMovieDetails
import com.givekesh.batmovies.data.entities.details.MovieDetails
import com.givekesh.batmovies.util.EntityMapper
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() : EntityMapper<CachedMovieDetails, MovieDetails> {
    override fun mapFromEntity(
        entity: CachedMovieDetails
    ): MovieDetails = MovieDetails(
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
        year = entity.year,
        ratings = listOf()
    )

    override fun mapToEntity(model: MovieDetails): CachedMovieDetails = CachedMovieDetails(
        actors = model.actors,
        awards = model.awards,
        boxOffice = model.boxOffice,
        country = model.country,
        dvd = model.dvd,
        director = model.director,
        genre = model.genre,
        imdbId = model.imdbId,
        imdbRating = model.imdbRating,
        imdbVotes = model.imdbVotes,
        language = model.language,
        metaScore = model.metaScore,
        plot = model.plot,
        poster = model.poster,
        production = model.production,
        rated = model.rated,
        released = model.released,
        response = model.response,
        runtime = model.runtime,
        title = model.title,
        type = model.type,
        website = model.website,
        writer = model.writer,
        year = model.year
    )
}