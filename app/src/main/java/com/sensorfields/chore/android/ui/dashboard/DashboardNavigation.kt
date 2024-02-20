package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sensorfields.chore.android.ui.chore.create.navigateToChoreCreate
import com.sensorfields.chore.android.ui.chore.create.onChoreCreateResult
import com.sensorfields.chore.android.ui.chore.details.navigateToChoreDetails
import com.sensorfields.chore.android.utils.LocalAppNavController

const val DASHBOARD_ROUTE = "dashboard"

fun NavGraphBuilder.dashboard() {
    composable(route = DASHBOARD_ROUTE) {
        val appNavController = LocalAppNavController.current
        val viewModel = hiltViewModel<DashboardViewModel>()

        appNavController.onChoreCreateResult(viewModel::onChoreCreateResult)

        val state by viewModel.state.collectAsStateWithLifecycle()
        DashboardScreen(
            state = state,
            actions = viewModel.actions,
            onChoreSortByClick = viewModel::onChoreSortByClick,
            onCreateChoreClick = appNavController::navigateToChoreCreate,
            onChoreClick = appNavController::navigateToChoreDetails
        )
    }
}
