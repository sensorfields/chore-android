package com.sensorfields.chore.android.domain.usecases

import com.sensorfields.chore.android.data.room.ChoreDao
import com.sensorfields.chore.android.domain.mappers.toModel
import com.sensorfields.chore.android.domain.models.Chore
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
public class ObserveChoreUseCase @Inject constructor(
    private val choreDao: ChoreDao,
) {
    public operator fun invoke(choreId: Chore.Id): Flow<Chore?> {
        return choreDao.observe(id = choreId.value).map { it?.toModel() }
    }
}
