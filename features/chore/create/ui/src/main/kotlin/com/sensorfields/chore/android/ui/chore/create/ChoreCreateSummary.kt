package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.android.ui.theme.TitleMediumText
import kotlinx.collections.immutable.ImmutableSet
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@Composable
internal fun ChoreCreateSummary(
    name: String,
    repeat: ChoreCreateState.When.Repeat,
    date: LocalDate?,
    time: LocalTime?,
    daysOfWeek: ImmutableSet<DayOfWeek>,
    daysOfMonth: ImmutableSet<Int>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TitleMediumText(name)
        Text("Repeat: $repeat")
        Text("Date: $date")
        Text("Time: $time")
        Text("Days of week: $daysOfWeek")
        Text("Days of month: $daysOfMonth")
    }
}
