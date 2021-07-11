package com.givekesh.batmovies.util

interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(model: DomainModel): Entity
}

interface EntityListMapper<Entity, DomainModel> : EntityMapper<Entity, DomainModel> {
    fun mapFromEntityList(entities: List<Entity>): List<DomainModel>
    fun mapToEntityList(models: List<DomainModel>): List<Entity>
}