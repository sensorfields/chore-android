package com.sensorfields.chore.android.domain.usecases

import com.sensorfields.chore.android.domain.mappers.toModel
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.data.room.ChoreDao
import com.sensorfields.chore.data.room.entities.ChoreEntity
import dev.zacsweers.metro.Inject
import java.time.Instant
import kotlin.uuid.Uuid

@Inject
public class CreateChoreUseCase(
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
