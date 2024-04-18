package com.sensorfields.chore.android.ui.dashboard

import com.sensorfields.chore.android.domain.models.Chore
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import java.time.Instant

internal data class DashboardState(
    val choreSort: ChoreSort = ChoreSort(sortBy = Chore.SortProperty.NAME, isAscending = true),
    val choreItems: ImmutableList<ChoreItem> = persistentListOf(),
) {
    data class ChoreSort(val sortBy: Chore.SortProperty, val isAscending: Boolean)
    data class ChoreItem(val id: Chore.Id, val name: String, val date: String?)
}

internal fun List<Chore>.toState(
    formatDate: (Instant?) -> String?,
): ImmutableList<DashboardState.ChoreItem> {
    return map {
        DashboardState.ChoreItem(
            id = it.id,
            name = it.name,
            date = formatDate(it.date)
        )
    }.toImmutableList()
}
