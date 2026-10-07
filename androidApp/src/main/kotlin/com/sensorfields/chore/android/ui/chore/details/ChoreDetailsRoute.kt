package com.sensorfields.chore.android.ui.chore.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sensorfields.chore.app.chore.details.ChoreDetailsViewModel
import dev.zacsweers.metrox.viewmodel.assistedMetroViewModel

@Composable
fun ChoreDetailsRoute(
    choreId: String,
    onNavigateUp: () -> Unit,
    viewModel: ChoreDetailsViewModel = assistedMetroViewModel<ChoreDetailsViewModel, ChoreDetailsViewModel.Factory> {
        create(choreId = choreId)
    },
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ChoreDetailsScreen(
        state = state,
        onUpClick = onNavigateUp,
    )
}
