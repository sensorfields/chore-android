package com.sensorfields.chore.android.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.time.Instant

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
    public var selectedDate: Instant?
        get() = state.selectedDateMillis?.let { Instant.ofEpochMilli(it) }
        set(value) {
            state.selectedDateMillis = value?.toEpochMilli()
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun rememberDatePickerState(
    initialSelectedDate: Instant? = null,
    initialDisplayedMonth: Instant? = initialSelectedDate,
): DatePickerState {
    val state = androidx.compose.material3.rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDate?.toEpochMilli(),
        initialDisplayedMonthMillis = initialDisplayedMonth?.toEpochMilli(),
    )
    return remember { DatePickerState(state = state) }
}
