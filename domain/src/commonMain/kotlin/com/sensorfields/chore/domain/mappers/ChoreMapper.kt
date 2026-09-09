package com.sensorfields.chore.domain.mappers

import com.sensorfields.chore.data.room.entities.ChoreEntity
import com.sensorfields.chore.domain.models.Chore
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun ChoreEntity.toModel(timeZone: TimeZone): Chore {
    val at = date?.toInstant() ?: error("No date for Chore")
    val dateTime = at.toLocalDateTime(timeZone)

    return Chore(
        id = Chore.Id(id),
        name = name,
        at = Chore.When.Once(
            time = dateTime.time,
            date = dateTime.date,
        ),
    )
}

internal fun List<ChoreEntity>.toModels(timeZone: TimeZone): List<Chore> = map { it.toModel(timeZone = timeZone) }

internal fun Chore.SortProperty.toEntity(): String {
    return when (this) {
        Chore.SortProperty.NAME -> "name"
        Chore.SortProperty.DATE -> "date"
    }
}
