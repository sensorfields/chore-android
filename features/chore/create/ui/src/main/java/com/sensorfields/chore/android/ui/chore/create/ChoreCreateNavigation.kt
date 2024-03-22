package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateNavigationAction.Finish
import com.sensorfields.chore.android.utils.collectInEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.onEach

public fun NavGraphBuilder.choreCreate(navController: NavController) {
    composable(route = ROUTE) {
        val viewModel = hiltViewModel<ChoreCreateViewModel>()
        viewModel.navigationAction.collectInEffect { action ->
            when (action) {
                is Finish -> {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        RESULT,
                        action.chore
                    )
                    navController.popBackStack()
                }
            }
        }
        val state by viewModel.state.collectAsStateWithLifecycle()
        ChoreCreateScreen(
            state = state,
            actions = viewModel.action,
            onUpClick = navController::navigateUp,
            onScreenChange = viewModel::onScreenChange,
            onNameChange = viewModel::onNameChange,
            onDateChange = viewModel::onDateChange,
            onNextClick = viewModel::onNextClick
        )
    }
}

public fun NavController.navigateToChoreCreate() {
    navigate(ROUTE)
}

public val NavController.choreCreateResults: Flow<Chore>
    get() {
        return currentBackStackEntry?.savedStateHandle?.getLiveData<Chore>(RESULT)
            ?.asFlow()
            ?.onEach { currentBackStackEntry?.savedStateHandle?.remove<Chore>(RESULT) }
            ?: emptyFlow()
    }

private const val ROUTE = "chore/create"
private const val RESULT = "$ROUTE/result"
