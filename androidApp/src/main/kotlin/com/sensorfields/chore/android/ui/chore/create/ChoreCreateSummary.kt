package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sensorfields.chore.app.chore.create.ChoreCreateState
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.TitleMediumText
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month

@Composable
fun ChoreCreateSummary(
    name: String,
    repeat: ChoreCreateState.When.Repeat,
    date: LocalDate?,
    time: LocalTime?,
    daysOfWeek: ImmutableSet<DayOfWeek>,
    daysOfMonth: ImmutableSet<Int>,
    months: ImmutableSet<Month>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TitleMediumText(name)
        Text("Repeat: $repeat")
        Text("Date: $date")
        Text("Time: $time")
        Text("Days of week: $daysOfWeek")
        Text("Days of month: $daysOfMonth")
        Text("Months: $months")
    }
}
