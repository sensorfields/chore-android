package com.sensorfields.chore.android.domain.usecases

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.domain.mappers.toModel
import com.sensorfields.chore.android.domain.mappers.toObjectId
import com.sensorfields.chore.android.domain.models.Chore
import dagger.Reusable
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
public class ObserveChoreUseCase @Inject constructor(
    private val realm: Realm,
) {
    public operator fun invoke(choreId: Chore.Id): Flow<Chore?> {
        return realm
            .query<ChoreEntity>("_id == $0", choreId.toObjectId())
            .first()
            .asFlow()
            .map { it.obj?.toModel() }
    }
}
