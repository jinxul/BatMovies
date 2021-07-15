package com.givekesh.batmovies.domain.mapper.details

import com.givekesh.batmovies.data.entities.details.CachedMovieDetails
import com.givekesh.batmovies.data.entities.details.CachedMovieDetailsWithRatings
import com.givekesh.batmovies.domain.entities.MovieDetails
import com.givekesh.batmovies.util.EntityMapper
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor(
    private val ratingsMapper: RatingsMapper
) : EntityMapper<CachedMovieDetailsWithRatings, MovieDetails> {
    override fun mapFromEntity(entity: CachedMovieDetailsWithRatings): MovieDetails = MovieDetails(
        actors = entity.details.actors,
        awards = entity.details.awards,
        boxOffice = entity.details.boxOffice,
        country = entity.details.country,
        dvd = entity.details.dvd,
        director = entity.details.director,
        genre = entity.details.genre,
        imdbId = entity.details.imdbId,
        imdbRating = entity.details.imdbRating,
        imdbVotes = entity.details.imdbVotes,
        language = entity.details.language,
        metaScore = entity.details.metaScore,
        plot = entity.details.plot,
        poster = entity.details.poster,
        production = entity.details.production,
        rated = entity.details.rated,
        released = entity.details.released,
        response = entity.details.response,
        runtime = entity.details.runtime,
        title = entity.details.title,
        type = entity.details.type,
        website = entity.details.website,
        writer = entity.details.writer,
        year = entity.details.year,
        ratings = ratingsMapper.mapFromEntityList(entity.ratings)
    )

    override fun mapToEntity(model: MovieDetails): CachedMovieDetailsWithRatings =
        CachedMovieDetailsWithRatings(
            details = mapToCachedMovieDetails(model),
            ratings = ratingsMapper.mapToEntityList(model.ratings, model.imdbId)
        )

    private fun mapToCachedMovieDetails(model: MovieDetails): CachedMovieDetails =
        CachedMovieDetails(
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