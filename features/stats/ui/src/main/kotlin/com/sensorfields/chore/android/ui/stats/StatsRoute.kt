package com.sensorfields.chore.android.ui.stats

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
public object StatsRoute

public fun NavGraphBuilder.stats() {
    composable<StatsRoute> {
        StatsScreen()
    }
}

public fun NavController.navigateToStats(navOptions: NavOptions) {
    navigate(StatsRoute, navOptions)
}
