package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.ui.collectInEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
public object DashboardRoute

public fun NavGraphBuilder.dashboard(
    onNavigateToChoreCreate: () -> Unit,
    choreCreateResults: () -> Flow<Chore>,
    onNavigateToChoreDetails: (Chore.Id) -> Unit,
) {
    composable<DashboardRoute> {
        val viewModel = hiltViewModel<DashboardViewModel>()

        choreCreateResults().collectInEffect(viewModel::onChoreCreateResult)

        val state by viewModel.state.collectAsStateWithLifecycle()
        DashboardScreen(
            state = state,
            actions = viewModel.actions,
            onChoreSortByClick = viewModel::onChoreSortByClick,
            onCreateChoreClick = onNavigateToChoreCreate,
            onChoreClick = onNavigateToChoreDetails
        )
    }
}

public fun NavController.navigateToDashboard(navOptions: NavOptions) {
    navigate(DashboardRoute, navOptions)
}
