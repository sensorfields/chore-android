package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.choreCreate(navController: NavController) {
    composable(route = ROUTE) {
        val viewModel = hiltViewModel<ChoreCreateViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        ChoreCreateScreen(
            state = state,
            action = viewModel.action,
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

fun SavedStateHandle.getChoreCreateResult(): Boolean? {
    return get<Boolean>(RESULT)?.also {
        remove<Boolean>(RESULT)
    }
}

private const val ROUTE = "chore/create"
private const val RESULT = "$ROUTE/result"
