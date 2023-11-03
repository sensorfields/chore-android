package com.sensorfields.chore.android.mappers

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.domain.models.Chore

fun List<ChoreEntity>.toModels(): List<Chore> = map { it.toModel() }

private fun ChoreEntity.toModel(): Chore {
    return Chore(
        id = id.toHexString(),
        name = name,
        date = date?.toInstant()
    )
}
