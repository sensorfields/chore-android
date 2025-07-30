package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.Icon
import com.sensorfields.chore.android.ui.theme.Icons
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.android.ui.theme.ToggleButton
import java.time.DayOfWeek
import java.time.format.TextStyle

@Composable
internal fun ChoreCreateWhenWeek(
    days: Set<DayOfWeek>,
    onDayCheckedChange: (DayOfWeek, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val locale = LocalConfiguration.current.locales[0]

    Column(modifier = modifier) {
        DayOfWeek.entries.forEach { day ->
            val checked = days.contains(day)
            ToggleButton(
                checked = checked,
                onCheckedChange = { onDayCheckedChange(day, it) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                if (checked) {
                    Icon(Icons.Check, contentDescription = null)
                }
                Text(day.getDisplayName(TextStyle.FULL, locale))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewEmpty() = AppTheme {
    ChoreCreateWhenWeek(
        days = emptySet(),
        onDayCheckedChange = { _, _ -> },
    )
}

@Preview
@Composable
private fun PreviewSome() = AppTheme {
    ChoreCreateWhenWeek(
        days = setOf(
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
        days = DayOfWeek.entries.toSet(),
        onDayCheckedChange = { _, _ -> },
    )
}
