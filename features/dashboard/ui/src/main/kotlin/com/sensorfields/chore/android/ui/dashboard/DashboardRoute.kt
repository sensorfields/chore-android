package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.ui.collectInEffect
import dev.zacsweers.metrox.viewmodel.metroViewModel
import kotlinx.coroutines.flow.Flow

@Composable
public fun DashboardRoute(
    onNavigateToChoreCreate: () -> Unit,
    choreCreateResults: () -> Flow<Chore>,
    onNavigateToChoreDetails: (Chore.Id) -> Unit,
) {
    val viewModel = metroViewModel<DashboardViewModel>()
    choreCreateResults().collectInEffect(viewModel::onChoreCreateResult)
    val state by viewModel.state.collectAsStateWithLifecycle()
    DashboardScreen(
        state = state,
        actions = viewModel.actions,
        onChoreSortByClick = viewModel::onChoreSortByClick,
        onCreateChoreClick = onNavigateToChoreCreate,
        onChoreClick = onNavigateToChoreDetails,
    )
}
