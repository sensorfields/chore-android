package com.sensorfields.chore.android.domain.usecase

import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.domain.mapper.toModels
import com.sensorfields.chore.android.domain.model.Chore
import dagger.Reusable
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
public class ObserveChoresUseCase @Inject constructor(
    private val realm: Realm,
) {
    public operator fun invoke(
        sortBy: Chore.SortProperty,
        isAscending: Boolean = true,
    ): Flow<List<Chore>> {
        return realm.query<ChoreEntity>()
            .sort(sortBy.toSortProperty(), isAscending.toSortOrder())
            .asFlow()
            .map { it.list.toModels() }
    }
}

private fun Chore.SortProperty.toSortProperty(): String {
    return when (this) {
        Chore.SortProperty.NAME -> "name"
        Chore.SortProperty.DATE -> "date"
    }
}

private fun Boolean.toSortOrder(): Sort {
    return when (this) {
        true -> Sort.ASCENDING
        false -> Sort.DESCENDING
    }
}
