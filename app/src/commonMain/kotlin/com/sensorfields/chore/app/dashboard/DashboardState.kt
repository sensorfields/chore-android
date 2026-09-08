package com.sensorfields.chore.app.dashboard

import com.sensorfields.chore.domain.models.Chore
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlin.time.Instant

public data class DashboardState(
    val choreSort: ChoreSort = ChoreSort(sortBy = Chore.SortProperty.NAME, isAscending = true),
    val choreItems: ImmutableList<ChoreItem> = persistentListOf(),
) {
    public data class ChoreSort(val sortBy: Chore.SortProperty, val isAscending: Boolean)
    public data class ChoreItem(val id: Chore.Id, val name: String, val date: Instant?)
}

public fun List<Chore>.toState(): ImmutableList<DashboardState.ChoreItem> {
    return map {
        DashboardState.ChoreItem(
            id = it.id,
            name = it.name,
            date = null, // TODO AT
        )
    }.toImmutableList()
}
