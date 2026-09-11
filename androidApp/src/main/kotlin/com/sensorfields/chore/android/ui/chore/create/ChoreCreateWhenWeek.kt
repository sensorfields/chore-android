package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewWrapper
import com.sensorfields.chore.app.SelectableItemState
import com.sensorfields.chore.app.format
import com.sensorfields.chore.app.generateSelectableItemState
import com.sensorfields.chore.theme.AppPreviewWrapper
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.ToggleButton
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.DayOfWeek

@Composable
fun ChoreCreateWhenWeek(
    items: ImmutableList<SelectableItemState<DayOfWeek>>,
    onDayCheckedChange: (DayOfWeek, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        items.forEach { item ->
            ToggleButton(
                checked = item.selected,
                onCheckedChange = { onDayCheckedChange(item.value, it) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(item.value.format())
            }
        }
    }
}

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun PreviewEmpty() {
    ChoreCreateWhenWeek(
        items = generateSelectableItemState(),
        onDayCheckedChange = { _, _ -> },
    )
}

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun PreviewSome() {
    ChoreCreateWhenWeek(
        items = generateSelectableItemState(
            setOf(
                DayOfWeek.WEDNESDAY,
                DayOfWeek.SATURDAY,
                DayOfWeek.SUNDAY,
            )
        ),
        onDayCheckedChange = { _, _ -> },
    )
}

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun PreviewAll() {
    ChoreCreateWhenWeek(
        items = generateSelectableItemState(DayOfWeek.entries.toSet()),
        onDayCheckedChange = { _, _ -> },
    )
}
