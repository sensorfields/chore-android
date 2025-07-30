package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.android.ui.theme.TitleMediumText
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@Composable
internal fun ChoreCreateSummary(
    name: String,
    repeat: ChoreCreateState.When.Repeat,
    date: LocalDate?,
    time: LocalTime?,
    days: Set<DayOfWeek>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TitleMediumText(name)
        Text("Repeat: $repeat")
        Text("Date: $date")
        Text("Time: $time")
        Text("Days of week: $days")
    }
}
