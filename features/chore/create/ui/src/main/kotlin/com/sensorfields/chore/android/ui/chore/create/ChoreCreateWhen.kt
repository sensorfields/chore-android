package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.ListItem
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.android.ui.theme.TitleMediumText

@Composable
internal fun ChoreCreateWhen(
    onRepeatClick: (ChoreCreateState.When.Repeat) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        TitleMediumText("Repeat")
        ListItem(
            headlineContent = { Text("Once") },
            modifier = Modifier.clickable { onRepeatClick(ChoreCreateState.When.Repeat.ONCE) },
        )
        ListItem(
            headlineContent = { Text("Daily") },
            modifier = Modifier.clickable { onRepeatClick(ChoreCreateState.When.Repeat.DAILY) },
        )
        ListItem(
            headlineContent = { Text("Weekly") },
            modifier = Modifier.clickable { onRepeatClick(ChoreCreateState.When.Repeat.WEEKLY) },
        )
        ListItem(
            headlineContent = { Text("Monthly") },
            modifier = Modifier.clickable { onRepeatClick(ChoreCreateState.When.Repeat.MONTHLY) },
        )
        ListItem(
            headlineContent = { Text("Yearly") },
            modifier = Modifier.clickable { onRepeatClick(ChoreCreateState.When.Repeat.YEARLY) },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = AppTheme {
    ChoreCreateWhen(
        onRepeatClick = {},
    )
}
