package com.sensorfields.chore.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.datetime.LocalDate
import kotlinx.datetime.YearMonth
import kotlinx.datetime.yearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun DatePicker(
    state: DatePickerState,
    modifier: Modifier = Modifier,
    showModeToggle: Boolean = true,
) {
    androidx.compose.material3.DatePicker(
        state = state.state,
        modifier = modifier,
        showModeToggle = showModeToggle,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
public class DatePickerState internal constructor(
    internal val state: androidx.compose.material3.DatePickerState,
) {
    public var selectedDate: LocalDate?
        get() = state.selectedDateMillis?.toLocalDateFromMilli()
        set(value) {
            state.selectedDateMillis = value?.toEpochMilli()
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun rememberDatePickerState(
    initialSelectedDate: LocalDate? = null,
    initialDisplayedMonth: YearMonth? = initialSelectedDate?.yearMonth,
): DatePickerState {
    val state = androidx.compose.material3.rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDate?.toEpochMilli(),
        initialDisplayedMonthMillis = initialDisplayedMonth?.firstDay?.toEpochMilli(),
    )
    return remember { DatePickerState(state = state) }
}

private fun LocalDate.toEpochMilli(): Long {
    return toEpochDays() * DAYS_TO_MILLIS
}

private fun Long.toLocalDateFromMilli(): LocalDate {
    return LocalDate.fromEpochDays(this / DAYS_TO_MILLIS)
}

private const val DAYS_TO_MILLIS = 24L * 60L * 60L * 1000L
