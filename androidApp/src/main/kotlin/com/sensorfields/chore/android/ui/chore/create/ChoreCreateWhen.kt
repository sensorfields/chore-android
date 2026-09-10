package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewWrapper
import com.sensorfields.chore.app.chore.create.ChoreCreateState
import com.sensorfields.chore.theme.AppPreviewWrapper
import com.sensorfields.chore.theme.ListItem
import com.sensorfields.chore.theme.Text

@Composable
fun ChoreCreateWhen(
    onRepeatClick: (ChoreCreateState.When.Repeat) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        ChoreCreateState.When.Repeat.entries.forEach { repeat ->
            ListItem(
                modifier = Modifier.clickable { onRepeatClick(repeat) },
            ) { Text(repeat.name) }
        }
    }
}

@Preview(showBackground = true)
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun Preview() {
    ChoreCreateWhen(
        onRepeatClick = {},
    )
}
