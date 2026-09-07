package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.ShowError
import com.sensorfields.chore.android.ui.collectInEffect
import com.sensorfields.chore.android.ui.toErrorMessage
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.chore_create_next_button
import com.sensorfields.chore.resources.chore_create_title
import com.sensorfields.chore.theme.AppTheme
import com.sensorfields.chore.theme.BottomBar
import com.sensorfields.chore.theme.CloseButton
import com.sensorfields.chore.theme.LoadingButton
import com.sensorfields.chore.theme.Scaffold
import com.sensorfields.chore.theme.SnackbarHost
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.TopAppBar
import com.sensorfields.chore.theme.rememberSnackbarHostState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChoreCreateScreen(
    state: ChoreCreateState,
    actions: Flow<ChoreCreateAction>,
    onUpClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onRepeatClick: (ChoreCreateState.When.Repeat) -> Unit,
    onDateChange: (LocalDate?) -> Unit,
    onTimeChange: (LocalTime) -> Unit,
    onDayOfWeekCheckedChange: (DayOfWeek, Boolean) -> Unit,
    onDayOfMonthCheckedChange: (Int, Boolean) -> Unit,
    onMonthCheckedChange: (Month, Boolean) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = rememberSnackbarHostState()

    actions.collectInEffect { action ->
        when (action) {
            is ShowError -> {
                snackbarHostState.showSnackbar(message = action.error.toErrorMessage())
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.chore_create_title)) },
                navigationIcon = { CloseButton(onClick = onUpClick) },
            )
        },
        bottomBar = {
            BottomBar {
                LoadingButton(
                    onClick = onNextClick,
                    loading = state.isLoadingVisible,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.isNextButtonEnabled,
                ) {
                    Text(stringResource(Res.string.chore_create_next_button))
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
                .imePadding(),
        ) {
            when (state) {
                is ChoreCreateState.What -> ChoreCreateWhat(
                    name = state.name,
                    onNameChange = onNameChange,
                    onDoneClick = onNextClick,
                )

                ChoreCreateState.When -> ChoreCreateWhen(
                    onRepeatClick = onRepeatClick,
                )

                is ChoreCreateState.WhenDate -> ChoreCreateWhenDate(
                    date = state.date,
                    onDateChange = onDateChange,
                )

                is ChoreCreateState.WhenTime -> ChoreCreateWhenTime(
                    time = state.time,
                    onTimeChange = onTimeChange,
                )

                is ChoreCreateState.WhenWeek -> ChoreCreateWhenWeek(
                    days = state.days,
                    onDayCheckedChange = onDayOfWeekCheckedChange,
                )

                is ChoreCreateState.WhenMonth -> ChoreCreateWhenMonth(
                    days = state.days,
                    onDayCheckedChange = onDayOfMonthCheckedChange,
                )

                is ChoreCreateState.WhenYear -> ChoreCreateWhenYear(
                    months = state.months,
                    onMonthCheckedChange = onMonthCheckedChange,
                )

                is ChoreCreateState.Summary -> ChoreCreateSummary(
                    name = state.name,
                    repeat = state.repeat,
                    date = state.date,
                    time = state.time,
                    daysOfWeek = state.daysOfWeek,
                    daysOfMonth = state.daysOfMonth,
                    months = state.months,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() = AppTheme {
    ChoreCreateScreen(
        state = ChoreCreateState.What(),
        actions = emptyFlow(),
        onUpClick = {},
        onNameChange = {},
        onRepeatClick = {},
        onDateChange = {},
        onTimeChange = {},
        onDayOfWeekCheckedChange = { _, _ -> },
        onDayOfMonthCheckedChange = { _, _ -> },
        onMonthCheckedChange = { _, _ -> },
        onNextClick = {},
    )
}
