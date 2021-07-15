package com.givekesh.batmovies.domain.mapper.details

import com.givekesh.batmovies.data.entities.details.CachedRatings
import com.givekesh.batmovies.domain.entities.Rating
import javax.inject.Inject

class RatingsMapper @Inject constructor() {
    private fun mapFromEntity(entity: CachedRatings): Rating = Rating(
        source = entity.source,
        value = entity.value
    )

    fun mapFromEntityList(entities: List<CachedRatings>): List<Rating> =
        entities.map { mapFromEntity(it) }

    private fun mapToEntity(model: Rating, id: String): CachedRatings = CachedRatings(
        movieId = id,
        source = model.source,
        value = model.value
    )

    fun mapToEntityList(models: List<Rating>, id: String): List<CachedRatings> =
        models.map { mapToEntity(it, id) }
}