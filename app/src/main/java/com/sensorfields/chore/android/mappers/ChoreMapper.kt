package com.sensorfields.chore.android.mappers

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.domain.model.Chore

fun List<ChoreEntity>.toModels(): List<Chore> = map { it.toModel() }

fun ChoreEntity.toModel(): Chore {
    return Chore(
        id = id.toHexString(),
        name = name,
        date = date?.toInstant()
    )
}
