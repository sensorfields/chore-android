package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

fun NavGraphBuilder.choreCreate(navController: NavController) {
    composable(route = ROUTE) {
        val viewModel = hiltViewModel<ChoreCreateViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        ChoreCreateScreen(
            state = state,
            actions = viewModel.action,
            onUpClick = navController::navigateUp,
            onScreenChange = viewModel::onScreenChange,
            onNameChange = viewModel::onNameChange,
            onDateChange = viewModel::onDateChange,
            onNextClick = viewModel::onNextClick,
            onFinish = {
                navController.previousBackStackEntry?.savedStateHandle?.set(RESULT, true)
                navController.navigateUp()
            }
        )
    }
}

fun NavController.navigateToChoreCreate() {
    navigate(ROUTE)
}

fun NavBackStackEntry.getChoreCreateResult(): Flow<Boolean> {
    return savedStateHandle.getLiveData<Boolean>(RESULT).asFlow()
        .onEach { savedStateHandle.remove<Boolean>(RESULT) }
}

private const val ROUTE = "chore/create"
private const val RESULT = "$ROUTE/result"
