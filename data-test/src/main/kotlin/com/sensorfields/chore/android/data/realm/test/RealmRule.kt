package com.sensorfields.chore.android.data.realm.test

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.junit.rules.ExternalResource

public class RealmRule : ExternalResource() {

    public lateinit var realm: Realm

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
