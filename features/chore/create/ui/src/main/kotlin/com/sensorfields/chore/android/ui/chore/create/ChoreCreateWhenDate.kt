package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.sensorfields.chore.android.ui.theme.DatePicker
import com.sensorfields.chore.android.ui.theme.rememberDatePickerState
import kotlinx.coroutines.flow.collectLatest
import java.time.Instant

@Composable
internal fun ChoreCreateWhenDate(
    date: Instant?,
    onDateChange: (Instant?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val datePickerState = rememberDatePickerState(initialSelectedDate = date)
    LaunchedEffect(datePickerState) {
        snapshotFlow { datePickerState.selectedDate }.collectLatest(onDateChange)
    }

    DatePicker(
        state = datePickerState,
        modifier = modifier,
    )
}
