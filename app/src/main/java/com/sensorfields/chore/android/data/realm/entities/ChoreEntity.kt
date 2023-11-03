package com.sensorfields.chore.android.data.realm.entities

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

@PersistedName("Chore")
class ChoreEntity : RealmObject {

    @PrimaryKey
    @PersistedName("_id")
    var id: ObjectId = ObjectId()

    var name: String = ""

    var date: RealmInstant? = null
}
