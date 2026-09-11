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
import androidx.compose.ui.tooling.preview.PreviewWrapper
import androidx.compose.ui.unit.dp
import com.sensorfields.chore.app.SelectableItemState
import com.sensorfields.chore.app.generateSelectableItemState
import com.sensorfields.chore.core.AppConfig
import com.sensorfields.chore.theme.AppPreviewWrapper
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.ToggleButton
import kotlinx.collections.immutable.ImmutableList

@Composable
fun ChoreCreateWhenMonth(
    items: ImmutableList<SelectableItemState<Int>>,
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
                    items.getOrNull(row * COLUMNS + column)?.let { item ->
                        ToggleButton(
                            checked = item.selected,
                            onCheckedChange = { onDayCheckedChange(item.value, it) },
                            modifier = Modifier.weight(1f),
                        ) {
                            Text("${item.value}")
                        }
                    } ?: Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

private const val ROWS = 5
private const val COLUMNS = 7

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun PreviewEmpty() {
    ChoreCreateWhenMonth(
        items = generateSelectableItemState(range = AppConfig.DAY_OF_MONTH_RANGE),
        onDayCheckedChange = { _, _ -> },
    )
}
