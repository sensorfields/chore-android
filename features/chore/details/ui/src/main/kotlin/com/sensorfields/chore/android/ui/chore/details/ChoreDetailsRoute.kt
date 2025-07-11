package com.sensorfields.chore.android.ui.chore.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sensorfields.chore.android.domain.models.Chore

@Composable
public fun ChoreDetailsRoute(
    choreId: Chore.Id,
    onNavigateUp: () -> Unit,
) {
    val viewModel = hiltViewModel<ChoreDetailsViewModel, ChoreDetailsViewModel.Factory> {
        it.create(choreId.value)
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    ChoreDetailsScreen(
        state = state,
        onUpClick = onNavigateUp,
    )
}
