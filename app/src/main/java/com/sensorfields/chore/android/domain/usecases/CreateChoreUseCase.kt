package com.sensorfields.chore.android.domain.usecases

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.mappers.toRealmInstant
import dagger.Reusable
import io.realm.kotlin.Realm
import java.time.Instant
import javax.inject.Inject

@Reusable
class CreateChoreUseCase @Inject constructor(
    private val realm: Realm,
) {
    suspend operator fun invoke(name: String, date: Instant?): Result {
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

    sealed interface Result {
        data object Success : Result
        data class Failure(val error: Exception) : Result
    }
}
