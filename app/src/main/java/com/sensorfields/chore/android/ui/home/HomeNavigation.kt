package com.sensorfields.chore.android.ui.home

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HOME_ROUTE = "home"

fun NavGraphBuilder.home(onNavigateToChoreCreate: () -> Unit) {
    composable(route = HOME_ROUTE) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        HomeScreen(
            state = state,
            onScreenChange = viewModel::onScreenChange,
            onCreateChoreClick = onNavigateToChoreCreate
        )
    }
}
