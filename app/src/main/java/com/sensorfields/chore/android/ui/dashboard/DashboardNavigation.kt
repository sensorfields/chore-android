package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sensorfields.chore.android.ui.LocalAppNavController
import com.sensorfields.chore.android.ui.chore.create.navigateToChoreCreate
import com.sensorfields.chore.android.ui.chore.create.onChoreCreateResult
import com.sensorfields.chore.android.ui.chore.view.navigateToChoreView
import logcat.logcat

const val DASHBOARD_ROUTE = "dashboard"

fun NavGraphBuilder.dashboard() {
    composable(route = DASHBOARD_ROUTE) {
        val appNavController = LocalAppNavController.current
        val viewModel = hiltViewModel<DashboardViewModel>()

        appNavController.onChoreCreateResult {
            logcat { "Chore Create Result: $it" }
        }

        val state by viewModel.state.collectAsStateWithLifecycle()
        DashboardScreen(
            state = state,
            onChoreSortByClick = viewModel::onChoreSortByClick,
            onCreateChoreClick = appNavController::navigateToChoreCreate,
            onChoreClick = appNavController::navigateToChoreView
        )
    }
}
