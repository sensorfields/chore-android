package com.sensorfields.chore.android.domain.mappers

import com.sensorfields.chore.android.data.room.entities.ChoreEntity
import com.sensorfields.chore.android.domain.models.Chore
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

internal fun ChoreEntity.toModel(): Chore {
    val at = date.let { Instant.parse(it) }
    return Chore(
        id = Chore.Id(id),
        name = name,
        at = Chore.When.Once(
            time = LocalTime.ofInstant(at, ZoneId.systemDefault()),
            date = LocalDate.ofInstant(at, ZoneId.systemDefault()),
        ),
    )
}

internal fun List<ChoreEntity>.toModels(): List<Chore> = map { it.toModel() }
