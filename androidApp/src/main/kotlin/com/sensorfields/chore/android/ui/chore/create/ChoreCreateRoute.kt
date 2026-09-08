package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sensorfields.chore.app.chore.create.ChoreCreateAction.Finish
import com.sensorfields.chore.app.chore.create.ChoreCreateAction.ShowError
import com.sensorfields.chore.app.chore.create.ChoreCreateViewModel
import com.sensorfields.chore.app.getMessage
import com.sensorfields.chore.core.collectInEffect
import com.sensorfields.chore.domain.models.Chore
import com.sensorfields.chore.theme.rememberSnackBarState
import dev.zacsweers.metrox.viewmodel.metroViewModel

@Composable
fun ChoreCreateRoute(
    onNavigateUp: () -> Unit,
    onFinish: (Chore) -> Unit,
    viewModel: ChoreCreateViewModel = metroViewModel(),
) {
    val snackBarState = rememberSnackBarState()

    val latestOnFinish by rememberUpdatedState(onFinish)
    viewModel.action.collectInEffect { action ->
        when (action) {
            is ShowError -> snackBarState.show(message = action.error.getMessage())
            is Finish -> latestOnFinish(action.chore)
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    ChoreCreateScreen(
        state = state,
        onUpClick = onNavigateUp,
        onNameChange = viewModel::onNameChange,
        onRepeatClick = viewModel::onRepeatClick,
        onDateChange = viewModel::onDateChange,
        onTimeChange = viewModel::onTimeChange,
        onDayOfWeekCheckedChange = viewModel::onDayOfWeekCheckedChange,
        onDayOfMonthCheckedChange = viewModel::onDayOfMonthCheckedChange,
        onMonthCheckedChange = viewModel::onMonthCheckedChange,
        onNextClick = viewModel::onNextClick,
        snackBarState = snackBarState,
    )
}
