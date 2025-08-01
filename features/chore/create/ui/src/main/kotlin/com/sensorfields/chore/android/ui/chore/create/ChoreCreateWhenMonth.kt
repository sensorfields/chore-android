package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.android.ui.theme.ToggleButton
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf

@Composable
internal fun ChoreCreateWhenMonth(
    days: ImmutableSet<Int>,
    onDayCheckedChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        repeat(ROWS) { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            ) {
                repeat(COLUMNS) { column ->
                    val day = row * COLUMNS + column + 1
                    if (day <= MAX_DAYS) {
                        ToggleButton(
                            checked = days.contains(day),
                            onCheckedChange = { onDayCheckedChange(day, it) },
                            modifier = Modifier.weight(1f),
                        ) {
                            Text("$day")
                        }
                    } else {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

private const val ROWS = 5
private const val COLUMNS = 7
private const val MAX_DAYS = 31

@Preview
@Composable
private fun PreviewEmpty() = AppTheme {
    ChoreCreateWhenMonth(
        days = persistentSetOf(),
        onDayCheckedChange = { _, _ -> },
    )
}
