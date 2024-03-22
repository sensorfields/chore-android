package com.sensorfields.chore.android.domain.usecases

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.domain.mappers.toModel
import com.sensorfields.chore.android.domain.mappers.toRealmInstant
import com.sensorfields.chore.android.domain.models.Chore
import dagger.Reusable
import io.realm.kotlin.Realm
import java.time.Instant
import javax.inject.Inject

@Reusable
public class CreateChoreUseCase @Inject constructor(
    private val realm: Realm,
) {
    public suspend operator fun invoke(name: String, date: Instant?): Result<Chore> {
        return try {
            val entity = realm.write {
                copyToRealm(ChoreEntity().apply {
                    this@apply.name = name
                    this@apply.date = date?.toRealmInstant()
                })
            }
            Result.success(entity.toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
