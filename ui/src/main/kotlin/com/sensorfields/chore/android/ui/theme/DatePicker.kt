package com.sensorfields.chore.android.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

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
    public var selectedDateMillis: Long?
        get() = state.selectedDateMillis
        set(value) {
            state.selectedDateMillis = value
        }
}

/**
 * TODO refactor to Instant API
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun rememberDatePickerState(
    initialSelectedDateMillis: Long? = null,
): DatePickerState {
    val state = androidx.compose.material3.rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDateMillis,
    )
    return remember { DatePickerState(state = state) }
}
