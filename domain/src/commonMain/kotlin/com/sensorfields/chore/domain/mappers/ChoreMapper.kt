package com.sensorfields.chore.domain.mappers

import com.sensorfields.chore.data.room.entities.ChoreEntity
import com.sensorfields.chore.domain.models.Chore
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun ChoreEntity.toModel(): Chore {
    val at = date?.toInstant() ?: error("No date for Chore")
    val dateTime = at.toLocalDateTime(TimeZone.currentSystemDefault()) // TODO TimeZone

    return Chore(
        id = Chore.Id(id),
        name = name,
        at = Chore.When.Once(
            time = dateTime.time,
            date = dateTime.date,
        ),
    )
}

internal fun List<ChoreEntity>.toModels(): List<Chore> = map { it.toModel() }
