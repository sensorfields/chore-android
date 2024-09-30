package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateNavigationAction.Finish
import com.sensorfields.chore.android.ui.collectInEffect
import com.sensorfields.chore.android.ui.observeResult
import com.sensorfields.chore.android.ui.setResult
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

public fun NavGraphBuilder.choreCreate(navController: NavController) {
    composable<ChoreCreateRoute> {
        val viewModel = hiltViewModel<ChoreCreateViewModel>()
        viewModel.navigationAction.collectInEffect { action ->
            when (action) {
                is Finish -> {
                    navController.previousBackStackEntry.setResult(RESULT, action.chore)
                    navController.popBackStack()
                }
            }
        }
        val state by viewModel.state.collectAsStateWithLifecycle()
        ChoreCreateScreen(
            state = state,
            actions = viewModel.action,
            onUpClick = navController::navigateUp,
            onNameChange = viewModel::onNameChange,
            onDateChange = viewModel::onDateChange,
            onNextClick = viewModel::onNextClick
        )
    }
}

public fun NavController.navigateToChoreCreate() {
    navigate(ChoreCreateRoute)
}

public val NavController.choreCreateResults: Flow<Chore>
    get() = currentBackStackEntry.observeResult(RESULT)

@Serializable
private object ChoreCreateRoute

private const val RESULT = "choreCreateResult"
