package com.sensorfields.chore.domain.usecases

import com.sensorfields.chore.core.logWarning
import com.sensorfields.chore.data.room.ChoreDao
import com.sensorfields.chore.data.room.entities.ChoreEntity
import com.sensorfields.chore.domain.mappers.toModel
import com.sensorfields.chore.domain.models.Chore
import com.sensorfields.chore.domain.models.Error
import dev.zacsweers.metro.Inject
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlin.uuid.Uuid

@Inject
public class CreateChoreUseCase(
    private val choreDao: ChoreDao,
    private val timeZone: TimeZone,
) {
    public suspend operator fun invoke(name: String, date: LocalDate, time: LocalTime): Result = try {
        val entity = ChoreEntity(
            id = Uuid.random().toString(),
            name = name,
            date = date.atTime(time).toInstant(timeZone = timeZone).toString(),
        )
        choreDao.insert(entity)
        Result.Success(entity.toModel(timeZone = timeZone))
    } catch (e: Exception) {
        logWarning { e }
        Result.Failure(e.toModel())
    }

    public sealed interface Result {
        public data class Success(val chore: Chore) : Result
        public data class Failure(val error: Error) : Result
    }
}
