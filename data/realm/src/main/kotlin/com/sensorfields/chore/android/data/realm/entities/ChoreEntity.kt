package com.sensorfields.chore.android.data.realm.entities

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

@PersistedName("Chore")
public class ChoreEntity : RealmObject {

    @PrimaryKey
    @PersistedName("_id")
    public var id: ObjectId = ObjectId()

    public var name: String = ""

    public var date: RealmInstant? = null
}
