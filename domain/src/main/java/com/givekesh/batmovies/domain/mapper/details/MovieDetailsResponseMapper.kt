package com.givekesh.batmovies.domain.mapper.details

import com.givekesh.batmovies.data.entities.details.CachedMovieDetails
import com.givekesh.batmovies.data.entities.details.CachedMovieDetailsWithRatings
import com.givekesh.batmovies.data.entities.details.MovieDetailsResponse
import com.givekesh.batmovies.domain.util.EntityMapper
import javax.inject.Inject

class MovieDetailsResponseMapper @Inject constructor(
    private val ratingsMapper: RatingsResponseMapper
) : EntityMapper<MovieDetailsResponse, CachedMovieDetailsWithRatings> {
    override fun mapFromEntity(entity: MovieDetailsResponse): CachedMovieDetailsWithRatings =
        CachedMovieDetailsWithRatings(
            details = mapToCachedMovieDetails(entity),
            ratings = ratingsMapper.mapToEntityList(entity.ratingResponses, entity.imdbId)
        )

    override fun mapToEntity(model: CachedMovieDetailsWithRatings): MovieDetailsResponse =
        MovieDetailsResponse(
            actors = model.details.actors,
            awards = model.details.awards,
            boxOffice = model.details.boxOffice,
            country = model.details.country,
            dvd = model.details.dvd,
            director = model.details.director,
            genre = model.details.genre,
            imdbId = model.details.imdbId,
            imdbRating = model.details.imdbRating,
            imdbVotes = model.details.imdbVotes,
            language = model.details.language,
            metaScore = model.details.metaScore,
            plot = model.details.plot,
            poster = model.details.poster,
            production = model.details.production,
            rated = model.details.rated,
            released = model.details.released,
            response = model.details.response,
            runtime = model.details.runtime,
            title = model.details.title,
            type = model.details.type,
            website = model.details.website,
            writer = model.details.writer,
            year = model.details.year,
            ratingResponses = ratingsMapper.mapFromEntityList(model.ratings)
        )

    private fun mapToCachedMovieDetails(model: MovieDetailsResponse): CachedMovieDetails =
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