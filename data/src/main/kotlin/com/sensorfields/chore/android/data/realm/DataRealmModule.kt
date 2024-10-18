package com.sensorfields.chore.android.data.realm

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataRealmModule {

    @Provides
    @Singleton
    fun realm(): Realm {
        return Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    ChoreEntity::class
                )
            )
        )
    }
}
