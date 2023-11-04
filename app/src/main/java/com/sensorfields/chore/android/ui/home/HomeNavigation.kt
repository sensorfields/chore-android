package com.sensorfields.chore.android.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HOME_ROUTE = "home"

fun NavGraphBuilder.home(onNavigateToChoreCreate: () -> Unit) {
    composable(route = HOME_ROUTE) {
        HomeScreen(onCreateChoreClick = onNavigateToChoreCreate)
    }
}
