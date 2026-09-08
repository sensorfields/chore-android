package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sensorfields.chore.app.dashboard.DashboardAction.ShowChoreCreatedMessage
import com.sensorfields.chore.app.dashboard.DashboardViewModel
import com.sensorfields.chore.core.collectInEffect
import com.sensorfields.chore.domain.models.Chore
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.dashboard_chore_created_message
import com.sensorfields.chore.theme.rememberSnackBarState
import dev.zacsweers.metrox.viewmodel.metroViewModel
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.getString

@Composable
fun DashboardRoute(
    onNavigateToChoreCreate: () -> Unit,
    choreCreateResults: () -> Flow<Chore>,
    onNavigateToChoreDetails: (Chore.Id) -> Unit,
    viewModel: DashboardViewModel = metroViewModel(),
) {
    choreCreateResults().collectInEffect(viewModel::onChoreCreateResult)

    val snackBarState = rememberSnackBarState()

    viewModel.actions.collectInEffect { action ->
        when (action) {
            is ShowChoreCreatedMessage -> snackBarState.show(
                message = getString(
                    Res.string.dashboard_chore_created_message,
                    action.choreName,
                ),
            )
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    DashboardScreen(
        state = state,
        onChoreSortByClick = viewModel::onChoreSortByClick,
        onCreateChoreClick = onNavigateToChoreCreate,
        onChoreClick = onNavigateToChoreDetails,
        snackBarState = snackBarState,
    )
}
