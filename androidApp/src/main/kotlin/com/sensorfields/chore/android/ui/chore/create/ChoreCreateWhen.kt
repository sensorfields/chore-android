package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.theme.AppTheme
import com.sensorfields.chore.theme.ListItem
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.TitleMediumText

@Composable
fun ChoreCreateWhen(
    onRepeatClick: (ChoreCreateState.When.Repeat) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        TitleMediumText("Repeat")
        ListItem(
            modifier = Modifier.clickable { onRepeatClick(ChoreCreateState.When.Repeat.ONCE) },
        ) { Text("Once") }
        ListItem(
            modifier = Modifier.clickable { onRepeatClick(ChoreCreateState.When.Repeat.DAILY) },
        ) { Text("Daily") }
        ListItem(
            modifier = Modifier.clickable { onRepeatClick(ChoreCreateState.When.Repeat.WEEKLY) },
        ) { Text("Weekly") }
        ListItem(
            modifier = Modifier.clickable { onRepeatClick(ChoreCreateState.When.Repeat.MONTHLY) },
        ) { Text("Monthly") }
        ListItem(
            modifier = Modifier.clickable { onRepeatClick(ChoreCreateState.When.Repeat.YEARLY) },
        ) { Text("Yearly") }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = AppTheme {
    ChoreCreateWhen(
        onRepeatClick = {},
    )
}
