package com.sensorfields.chore.android.ui.dashboard

import com.sensorfields.chore.android.domain.models.Chore
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import java.time.Instant

data class DashboardState(
    val choreSort: ChoreSort = ChoreSort(sortBy = ChoreSortBy.NAME, isAscending = true),
    val chores: ImmutableList<Chore> = persistentListOf(),
) {
    enum class ChoreSortBy { NAME, DATE }
    data class ChoreSort(val sortBy: ChoreSortBy, val isAscending: Boolean)
    data class Chore(val id: String, val name: String, val date: Instant?)
}

fun List<Chore>.toState(): ImmutableList<DashboardState.Chore> {
    return map {
        DashboardState.Chore(
            id = it.id,
            name = it.name,
            date = it.date
        )
    }.toImmutableList()
}
