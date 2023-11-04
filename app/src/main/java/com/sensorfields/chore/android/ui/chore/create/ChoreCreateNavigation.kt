package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.choreCreate(onNavigateUp: () -> Unit) {
    composable(route = ROUTE) {
        val viewModel = hiltViewModel<ChoreCreateViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        ChoreCreateScreen(
            state = state,
            action = viewModel.action,
            onUpClick = onNavigateUp,
            onScreenChange = viewModel::onScreenChange,
            onNameChange = viewModel::onNameChange,
            onDateChange = viewModel::onDateChange,
            onNextClick = viewModel::onNextClick
        )
    }
}

fun NavController.navigateToChoreCreate() {
    navigate(ROUTE)
}

private const val ROUTE = "chore/create"
