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
        // TODO order
        return choreDao.find(sortBy.toSortProperty()).map { it.toModels() }

//        return realm.query<ChoreEntity>()
//            .sort(sortBy.toSortProperty(), isAscending.toSortOrder())
//            .asFlow()
//            .map { it.list.toModels() }
    }
}

private fun Chore.SortProperty.toSortProperty(): String {
    return when (this) {
        Chore.SortProperty.NAME -> "name"
        Chore.SortProperty.DATE -> "date"
    }
}

