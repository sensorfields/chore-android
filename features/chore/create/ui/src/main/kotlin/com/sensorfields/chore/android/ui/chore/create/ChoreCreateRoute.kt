package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateNavigationAction.Finish
import com.sensorfields.chore.android.ui.collectInEffect

@Composable
public fun ChoreCreateRoute(
    onNavigateUp: () -> Unit,
    onFinish: (Chore) -> Unit,
) {
    val viewModel = hiltViewModel<ChoreCreateViewModel>()
    viewModel.navigationAction.collectInEffect { action ->
        when (action) {
            is Finish -> onFinish(action.chore)
        }
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    ChoreCreateScreen(
        state = state,
        actions = viewModel.action,
        onUpClick = onNavigateUp,
        onNameChange = viewModel::onNameChange,
        onDateChange = viewModel::onDateChange,
        onNextClick = viewModel::onNextClick,
    )
}
