package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewWrapper
import com.sensorfields.chore.theme.AppPreviewWrapper
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.ToggleButton
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.datetime.DayOfWeek

@Composable
fun ChoreCreateWhenWeek(
    days: ImmutableSet<DayOfWeek>,
    onDayCheckedChange: (DayOfWeek, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        DayOfWeek.entries.forEach { day ->
            ToggleButton(
                checked = days.contains(day),
                onCheckedChange = { onDayCheckedChange(day, it) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(day.name) // TODO format day
            }
        }
    }
}

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun PreviewEmpty() {
    ChoreCreateWhenWeek(
        days = persistentSetOf(),
        onDayCheckedChange = { _, _ -> },
    )
}

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun PreviewSome() {
    ChoreCreateWhenWeek(
        days = persistentSetOf(
            DayOfWeek.WEDNESDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY,
        ),
        onDayCheckedChange = { _, _ -> },
    )
}

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun PreviewAll() {
    ChoreCreateWhenWeek(
        days = DayOfWeek.entries.toImmutableSet(),
        onDayCheckedChange = { _, _ -> },
    )
}
