package com.sensorfields.chore.android.domain.usecase

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.domain.mapper.toRealmInstant
import dagger.Reusable
import io.realm.kotlin.Realm
import java.time.Instant
import javax.inject.Inject

@Reusable
public class CreateChoreUseCase @Inject constructor(
    private val realm: Realm,
) {
    public suspend operator fun invoke(name: String, date: Instant?): Result {
        return try {
            realm.write {
                copyToRealm(ChoreEntity().apply {
                    this@apply.name = name
                    this@apply.date = date?.toRealmInstant()
                })
            }
            Result.Success
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    public sealed interface Result {
        public data object Success : Result
        public data class Failure(val error: Exception) : Result
    }
}
