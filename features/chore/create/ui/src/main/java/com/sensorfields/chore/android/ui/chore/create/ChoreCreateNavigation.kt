package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sensorfields.chore.android.utils.LocalAppNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

public fun NavGraphBuilder.choreCreate() {
    composable(route = ROUTE) {
        val appNavController = LocalAppNavController.current
        val viewModel = hiltViewModel<ChoreCreateViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        ChoreCreateScreen(
            state = state,
            actions = viewModel.action,
            onUpClick = appNavController::navigateUp,
            onScreenChange = viewModel::onScreenChange,
            onNameChange = viewModel::onNameChange,
            onDateChange = viewModel::onDateChange,
            onNextClick = viewModel::onNextClick,
            onFinish = {
                appNavController.previousBackStackEntry?.savedStateHandle?.set(RESULT, true)
                appNavController.navigateUp()
            }
        )
    }
}

public fun NavController.navigateToChoreCreate() {
    navigate(ROUTE)
}

public val NavController.choreCreateResults: Flow<Boolean>?
    get() {
        return currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(RESULT)?.asFlow()
            ?.onEach { currentBackStackEntry?.savedStateHandle?.remove<Boolean>(RESULT) }
    }

private const val ROUTE = "chore/create"
private const val RESULT = "$ROUTE/result"
