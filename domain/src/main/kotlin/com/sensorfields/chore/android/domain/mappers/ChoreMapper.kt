package com.sensorfields.chore.android.domain.mappers

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.domain.models.Chore
import org.mongodb.kbson.ObjectId

internal fun ObjectId.toChoreId(): Chore.Id = Chore.Id(toHexString())

internal fun Chore.Id.toObjectId(): ObjectId = ObjectId(value)

internal fun ChoreEntity.toModel(): Chore {
    return Chore(
        id = id.toChoreId(),
        name = name,
        date = date?.toInstant()
    )
}

internal fun List<ChoreEntity>.toModels(): List<Chore> = map { it.toModel() }
