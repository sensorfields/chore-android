package com.sensorfields.chore.android.domain.mapper

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.domain.model.Chore

public fun List<ChoreEntity>.toModels(): List<Chore> = map { it.toModel() }

public fun ChoreEntity.toModel(): Chore {
    return Chore(
        id = id.toHexString(),
        name = name,
        date = date?.toInstant()
    )
}
