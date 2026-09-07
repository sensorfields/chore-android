package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.android.ui.theme.ToggleButton
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.datetime.Month

@Composable
fun ChoreCreateWhenYear(
    months: ImmutableSet<Month>,
    onMonthCheckedChange: (Month, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Month.entries.forEach { month ->
            ToggleButton(
                checked = months.contains(month),
                onCheckedChange = { onMonthCheckedChange(month, it) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(month.name) // TODO format month
            }
        }
    }
}

@Preview
@Composable
private fun PreviewEmpty() = AppTheme {
    ChoreCreateWhenYear(
        months = persistentSetOf(),
        onMonthCheckedChange = { _, _ -> },
    )
}

@Preview
@Composable
private fun PreviewSome() = AppTheme {
    ChoreCreateWhenYear(
        months = persistentSetOf(Month.JANUARY, Month.FEBRUARY, Month.NOVEMBER),
        onMonthCheckedChange = { _, _ -> },
    )
}

@Preview
@Composable
private fun PreviewAll() = AppTheme {
    ChoreCreateWhenYear(
        months = Month.entries.toImmutableSet(),
        onMonthCheckedChange = { _, _ -> },
    )
}
