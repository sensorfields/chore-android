package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sensorfields.chore.android.ui.chore.choreDate
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.DatePicker
import com.sensorfields.chore.android.ui.theme.TitleMediumText
import com.sensorfields.chore.android.ui.theme.rememberDatePickerState
import kotlinx.coroutines.flow.collectLatest
import java.time.Instant

@Composable
internal fun ColumnScope.ChoreCreateWhenItem(
    isExpanded: Boolean,
    date: Instant?,
    onDateChange: (Instant?) -> Unit,
) {
    val datePickerState = rememberDatePickerState(initialSelectedDate = date)

    LaunchedEffect(datePickerState) {
        snapshotFlow { datePickerState.selectedDate }.collectLatest(onDateChange)
    }

    if (isExpanded) {
        DatePicker(state = datePickerState)
    } else if (date != null) {
        TitleMediumText(
            choreDate(date),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewExpanded() = AppTheme {
    Column {
        ChoreCreateWhenItem(
            isExpanded = true,
            date = null,
            onDateChange = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCollapsed() = AppTheme {
    Column {
        ChoreCreateWhenItem(
            isExpanded = false,
            date = Instant.parse("1988-02-13T13:30:00Z"),
            onDateChange = {},
        )
    }
}
