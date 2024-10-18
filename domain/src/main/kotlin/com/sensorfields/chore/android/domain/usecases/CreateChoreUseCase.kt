package com.sensorfields.chore.android.domain.usecases

import com.sensorfields.chore.android.data.room.ChoreDao
import com.sensorfields.chore.android.data.room.entities.ChoreEntity
import com.sensorfields.chore.android.domain.mappers.toModel
import com.sensorfields.chore.android.domain.models.Chore
import dagger.Reusable
import java.time.Instant
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Reusable
public class CreateChoreUseCase @Inject constructor(
    private val choreDao: ChoreDao,
) {
    public suspend operator fun invoke(name: String, date: Instant?): Result<Chore> {
        return try {
            val entity = ChoreEntity(
                id = Uuid.random().toString(),
                name = name,
                date = date?.toString(),
            )
            choreDao.insert(entity)
            Result.success(entity.toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
