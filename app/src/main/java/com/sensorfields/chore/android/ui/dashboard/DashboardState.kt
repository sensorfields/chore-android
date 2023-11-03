package com.sensorfields.chore.android.ui.dashboard

import com.sensorfields.chore.android.domain.models.Chore
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class DashboardState(
    val chores: ImmutableList<Chore> = persistentListOf(),
) {
    data class Chore(val id: String, val name: String)
}

fun List<Chore>.toState(): ImmutableList<DashboardState.Chore> {
    return map {
        DashboardState.Chore(
            id = it.id,
            name = it.name
        )
    }.toImmutableList()
}
