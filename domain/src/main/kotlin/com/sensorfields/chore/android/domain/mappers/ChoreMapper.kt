package com.sensorfields.chore.android.domain.mappers

import com.sensorfields.chore.android.data.room.entities.ChoreEntity
import com.sensorfields.chore.android.domain.models.Chore
import java.time.Instant

internal fun ChoreEntity.toModel(): Chore {
    return Chore(
        id = Chore.Id(id),
        name = name,
        date = date?.let { Instant.parse(it) },
    )
}

internal fun List<ChoreEntity>.toModels(): List<Chore> = map { it.toModel() }
