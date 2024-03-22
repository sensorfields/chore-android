package com.sensorfields.chore.android.ui.stats

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

public const val STATS_ROUTE: String = "stats"

public fun NavGraphBuilder.stats() {
    composable(route = STATS_ROUTE) {
        StatsScreen()
    }
}
