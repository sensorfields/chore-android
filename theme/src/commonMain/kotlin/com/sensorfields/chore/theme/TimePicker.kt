package com.sensorfields.chore.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.datetime.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun TimePicker(
    state: TimePickerState,
    modifier: Modifier = Modifier,
) {
    androidx.compose.material3.TimePicker(
        state = state.state,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
public class TimePickerState internal constructor(
    internal val state: androidx.compose.material3.TimePickerState,
) {
    public var selectedTime: LocalTime
        get() = LocalTime(hour = state.hour, minute = state.minute)
        set(value) {
            state.hour = value.hour
            state.minute = value.minute
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun rememberTimePickerState(
    initialTime: LocalTime? = null,
): TimePickerState {
    val state = androidx.compose.material3.rememberTimePickerState(
        initialHour = initialTime?.hour ?: 0,
        initialMinute = initialTime?.minute ?: 0,
    )
    return remember { TimePickerState(state = state) }
}
