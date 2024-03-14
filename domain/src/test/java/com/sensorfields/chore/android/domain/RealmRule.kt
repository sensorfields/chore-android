package com.sensorfields.chore.android.domain

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.junit.rules.ExternalResource

class RealmRule : ExternalResource() {

    lateinit var realm: Realm

    override fun before() {
        realm = Realm.open(
            RealmConfiguration.Builder(schema = setOf(ChoreEntity::class))
                .inMemory()
                .build()
        )
    }

    override fun after() {
        realm.close()
    }
}
