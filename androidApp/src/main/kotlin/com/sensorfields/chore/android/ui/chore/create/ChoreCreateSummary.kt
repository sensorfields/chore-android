package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sensorfields.chore.app.SelectableItemState
import com.sensorfields.chore.app.chore.create.ChoreCreateState
import com.sensorfields.chore.app.format
import com.sensorfields.chore.app.selected
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.TitleMediumText
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month

@Composable
fun ChoreCreateSummary(
    name: String,
    repeat: ChoreCreateState.Repeat?,
    date: LocalDate,
    time: LocalTime,
    daysOfWeek: ImmutableList<SelectableItemState<DayOfWeek>>,
    daysOfMonth: ImmutableList<SelectableItemState<Int>>,
    months: ImmutableList<SelectableItemState<Month>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TitleMediumText(name)
        Text("Repeat: $repeat")
        Text("Date: ${date.format()}")
        Text("Time: ${time.format()}")
        Text("Days of week: ${daysOfWeek.selected()}")
        Text("Days of month: ${daysOfMonth.selected()}")
        Text("Months: ${months.selected()}")
    }
}
