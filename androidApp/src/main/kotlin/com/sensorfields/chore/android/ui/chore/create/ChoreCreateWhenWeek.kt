package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.android.ui.theme.ToggleButton
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toImmutableSet
import java.time.DayOfWeek
import java.time.format.TextStyle

@Composable
fun ChoreCreateWhenWeek(
    days: ImmutableSet<DayOfWeek>,
    onDayCheckedChange: (DayOfWeek, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val locale = LocalConfiguration.current.locales[0]

    Column(modifier = modifier) {
        DayOfWeek.entries.forEach { day ->
            ToggleButton(
                checked = days.contains(day),
                onCheckedChange = { onDayCheckedChange(day, it) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(day.getDisplayName(TextStyle.FULL, locale))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewEmpty() = AppTheme {
    ChoreCreateWhenWeek(
        days = persistentSetOf(),
        onDayCheckedChange = { _, _ -> },
    )
}

@Preview
@Composable
private fun PreviewSome() = AppTheme {
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
@Composable
private fun PreviewAll() = AppTheme {
    ChoreCreateWhenWeek(
        days = DayOfWeek.entries.toImmutableSet(),
        onDayCheckedChange = { _, _ -> },
    )
}
