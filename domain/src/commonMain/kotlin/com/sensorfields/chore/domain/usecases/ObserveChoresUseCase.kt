package com.sensorfields.chore.domain.usecases

import com.sensorfields.chore.data.room.ChoreDao
import com.sensorfields.chore.domain.mappers.toModels
import com.sensorfields.chore.domain.models.Chore
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Inject
public class ObserveChoresUseCase(
    private val choreDao: ChoreDao,
) {
    public operator fun invoke(
        sortBy: Chore.SortProperty,
        isAscending: Boolean = true,
    ): Flow<List<Chore>> {
        return choreDao
            .find(orderBy = sortBy.toSortProperty(), isAscending = isAscending)
            .map { it.toModels() }
    }
}

private fun Chore.SortProperty.toSortProperty(): String {
    return when (this) {
        Chore.SortProperty.NAME -> "name"
        Chore.SortProperty.DATE -> "date"
    }
}
