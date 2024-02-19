package com.sensorfields.chore.android.domain.usecase

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.domain.mapper.toModel
import com.sensorfields.chore.android.domain.model.Chore
import dagger.Reusable
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@Reusable
public class ObserveChoreUseCase @Inject constructor(
    private val realm: Realm,
) {
    public operator fun invoke(choreId: String): Flow<Result<Chore>> {
        return realm
            .query<ChoreEntity>("_id == $0", ObjectId(choreId))
            .first()
            .asFlow()
            .map { change ->
                change.obj?.toModel()?.let { Result.success(it) }
                    ?: Result.failure(IllegalArgumentException())
            }
    }
}
