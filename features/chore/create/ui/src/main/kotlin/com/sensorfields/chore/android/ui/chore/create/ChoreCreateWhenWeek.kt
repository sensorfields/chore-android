package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.sensorfields.chore.android.ui.theme.ButtonGroup
import java.time.DayOfWeek
import java.time.format.TextStyle

@Composable
internal fun ChoreCreateWhenWeek(
    days: Set<DayOfWeek>,
    onDayCheckedChange: (DayOfWeek, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val locale = LocalConfiguration.current.locales[0]

    ButtonGroup(
        modifier = Modifier.fillMaxWidth(),
    ) {
        DayOfWeek.entries.forEach { day ->
            toggleableItem(
                checked = days.contains(day),
                label = day.getDisplayName(TextStyle.SHORT, locale),
                onCheckedChange = { onDayCheckedChange(day, it) },
            )
        }
    }
}
