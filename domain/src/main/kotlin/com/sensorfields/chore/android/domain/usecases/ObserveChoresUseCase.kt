package com.sensorfields.chore.android.domain.usecases

import com.sensorfields.chore.android.data.room.ChoreDao
import com.sensorfields.chore.android.domain.mappers.toModels
import com.sensorfields.chore.android.domain.models.Chore
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
public class ObserveChoresUseCase @Inject constructor(
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
