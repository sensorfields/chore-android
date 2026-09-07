package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateNavigationAction.Finish
import com.sensorfields.chore.android.ui.collectInEffect
import com.sensorfields.chore.domain.models.Chore
import dev.zacsweers.metrox.viewmodel.metroViewModel

@Composable
fun ChoreCreateRoute(
    onNavigateUp: () -> Unit,
    onFinish: (Chore) -> Unit,
) {
    val viewModel = metroViewModel<ChoreCreateViewModel>()
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
        onRepeatClick = viewModel::onRepeatClick,
        onDateChange = viewModel::onDateChange,
        onTimeChange = viewModel::onTimeChange,
        onDayOfWeekCheckedChange = viewModel::onDayOfWeekCheckedChange,
        onDayOfMonthCheckedChange = viewModel::onDayOfMonthCheckedChange,
        onMonthCheckedChange = viewModel::onMonthCheckedChange,
        onNextClick = viewModel::onNextClick,
    )
}
