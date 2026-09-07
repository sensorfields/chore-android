package com.sensorfields.chore.domain.usecases

import com.sensorfields.chore.data.room.ChoreDao
import com.sensorfields.chore.domain.mappers.toModel
import com.sensorfields.chore.domain.models.Chore
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Inject
public class ObserveChoreUseCase(
    private val choreDao: ChoreDao,
) {
    public operator fun invoke(choreId: Chore.Id): Flow<Chore?> {
        return choreDao.observe(id = choreId.value).map { it?.toModel() }
    }
}
