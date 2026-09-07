package com.sensorfields.chore.android.ui.chore.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sensorfields.chore.domain.models.Chore
import dev.zacsweers.metrox.viewmodel.assistedMetroViewModel

@Composable
fun ChoreDetailsRoute(
    choreId: Chore.Id,
    onNavigateUp: () -> Unit,
) {
    val viewModel = assistedMetroViewModel<ChoreDetailsViewModel, ChoreDetailsViewModel.Factory> {
        create(choreId.value)
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    ChoreDetailsScreen(
        state = state,
        onUpClick = onNavigateUp,
    )
}
