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
import androidx.compose.ui.tooling.preview.PreviewWrapper
import com.sensorfields.chore.app.chore.create.ChoreCreateState
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.chore_create_next_button
import com.sensorfields.chore.resources.chore_create_title
import com.sensorfields.chore.theme.AppPreviewWrapper
import com.sensorfields.chore.theme.BottomBar
import com.sensorfields.chore.theme.CloseButton
import com.sensorfields.chore.theme.LoadingButton
import com.sensorfields.chore.theme.Scaffold
import com.sensorfields.chore.theme.SnackBarState
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.TopAppBar
import com.sensorfields.chore.theme.rememberSnackBarState
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChoreCreateScreen(
    state: ChoreCreateState,
    onUpClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onRepeatClick: (ChoreCreateState.Repeat) -> Unit,
    onDateChange: (LocalDate?) -> Unit,
    onTimeChange: (LocalTime) -> Unit,
    onDayOfWeekCheckedChange: (DayOfWeek, Boolean) -> Unit,
    onDayOfMonthCheckedChange: (Int, Boolean) -> Unit,
    onMonthCheckedChange: (Month, Boolean) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    snackBarState: SnackBarState = rememberSnackBarState(),
) {
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
        snackBarState = snackBarState,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
                .imePadding(),
        ) {
            when (state.step) {
                ChoreCreateState.Step.WHAT -> ChoreCreateWhat(
                    name = state.name,
                    onNameChange = onNameChange,
                    onDoneClick = onNextClick,
                )

                ChoreCreateState.Step.WHEN -> ChoreCreateWhen(
                    onRepeatClick = onRepeatClick,
                )

                ChoreCreateState.Step.WHEN_DATE -> ChoreCreateWhenDate(
                    date = state.date,
                    onDateChange = onDateChange,
                )

                ChoreCreateState.Step.WHEN_TIME -> ChoreCreateWhenTime(
                    time = state.time,
                    onTimeChange = onTimeChange,
                )

                ChoreCreateState.Step.WHEN_WEEK -> ChoreCreateWhenWeek(
                    items = state.daysOfWeek,
                    onDayCheckedChange = onDayOfWeekCheckedChange,
                )

                ChoreCreateState.Step.WHEN_MONTH -> ChoreCreateWhenMonth(
                    items = state.daysOfMonth,
                    onDayCheckedChange = onDayOfMonthCheckedChange,
                )

                ChoreCreateState.Step.WHEN_YEAR -> ChoreCreateWhenYear(
                    items = state.months,
                    onMonthCheckedChange = onMonthCheckedChange,
                )

                ChoreCreateState.Step.SUMMARY -> ChoreCreateSummary(
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
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun Preview() {
    ChoreCreateScreen(
        state = ChoreCreateState.initial(),
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
