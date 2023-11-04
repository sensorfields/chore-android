package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val DASHBOARD_ROUTE = "dashboard"

fun NavGraphBuilder.dashboard(onNavigateToChoreUpdate: (String) -> Unit) {
    composable(route = DASHBOARD_ROUTE) {
        val viewModel = hiltViewModel<DashboardViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        DashboardScreen(
            state = state,
            onChoreClick = onNavigateToChoreUpdate
        )
    }
}
