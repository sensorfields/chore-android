package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.sensorfields.chore.android.ui.theme.TimePicker
import com.sensorfields.chore.android.ui.theme.rememberTimePickerState
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalTime

@Composable
internal fun ChoreCreateWhenTime(
    time: LocalTime?,
    onTimeChange: (LocalTime) -> Unit,
    modifier: Modifier = Modifier,
) {
    val timePickerState = rememberTimePickerState(initialTime = time)
    LaunchedEffect(timePickerState) {
        snapshotFlow { timePickerState.selectedTime }.collectLatest(onTimeChange)
    }
    TimePicker(state = timePickerState, modifier = modifier)
}
