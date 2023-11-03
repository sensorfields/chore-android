package com.sensorfields.chore.android.domain.usecases

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.mappers.toModels
import dagger.Reusable
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class ObserveChoresUseCase @Inject constructor(
    private val realm: Realm,
) {
    operator fun invoke(): Flow<List<Chore>> {
        return realm.query<ChoreEntity>().asFlow().map { it.list.toModels() }
    }
}
