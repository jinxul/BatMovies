package com.givekesh.batmovies.domain.mapper.details

import com.givekesh.batmovies.data.entities.details.CachedRatings
import com.givekesh.batmovies.data.entities.details.RatingResponse
import javax.inject.Inject

class RatingsResponseMapper @Inject constructor() {
    private fun mapFromEntity(entity: CachedRatings): RatingResponse = RatingResponse(
        source = entity.source,
        value = entity.value
    )

    fun mapFromEntityList(entities: List<CachedRatings>): List<RatingResponse> =
        entities.map { mapFromEntity(it) }

    private fun mapToEntity(model: RatingResponse, id: String): CachedRatings = CachedRatings(
        movieId = id,
        source = model.source,
        value = model.value
    )

    fun mapToEntityList(models: List<RatingResponse>, id: String): List<CachedRatings> =
        models.map { mapToEntity(it, id) }
}