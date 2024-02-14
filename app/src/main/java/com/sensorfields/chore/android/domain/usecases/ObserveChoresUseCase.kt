package com.sensorfields.chore.android.domain.usecases

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.mappers.toModels
import com.sensorfields.chore.android.ui.dashboard.DashboardState
import dagger.Reusable
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class ObserveChoresUseCase @Inject constructor(
    private val realm: Realm,
) {
    operator fun invoke(sort: DashboardState.ChoreSort): Flow<List<Chore>> {
        return realm.query<ChoreEntity>()
            .sort(sort.toSortProperty(), sort.toSortOrder())
            .asFlow()
            .map { it.list.toModels() }
    }
}

private fun DashboardState.ChoreSort.toSortProperty(): String {
    return when (sortBy) {
        DashboardState.ChoreSortBy.NAME -> "name"
        DashboardState.ChoreSortBy.DATE -> "date"
    }
}

private fun DashboardState.ChoreSort.toSortOrder(): Sort {
    return when (isAscending) {
        true -> Sort.ASCENDING
        false -> Sort.DESCENDING
    }
}
