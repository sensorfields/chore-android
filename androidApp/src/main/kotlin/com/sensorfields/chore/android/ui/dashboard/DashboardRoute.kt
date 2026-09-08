package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sensorfields.chore.app.dashboard.DashboardViewModel
import com.sensorfields.chore.core.collectInEffect
import com.sensorfields.chore.domain.models.Chore
import dev.zacsweers.metrox.viewmodel.metroViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun DashboardRoute(
    onNavigateToChoreCreate: () -> Unit,
    choreCreateResults: () -> Flow<Chore>,
    onNavigateToChoreDetails: (Chore.Id) -> Unit,
    viewModel: DashboardViewModel = metroViewModel(),
) {
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
