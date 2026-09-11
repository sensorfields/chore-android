package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewWrapper
import com.sensorfields.chore.app.SelectableItemState
import com.sensorfields.chore.app.generateSelectableItemState
import com.sensorfields.chore.theme.AppPreviewWrapper
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.ToggleButton
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.Month

@Composable
fun ChoreCreateWhenYear(
    items: ImmutableList<SelectableItemState<Month>>,
    onMonthCheckedChange: (Month, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        items.forEach { item ->
            ToggleButton(
                checked = item.selected,
                onCheckedChange = { onMonthCheckedChange(item.value, it) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(item.value.name) // TODO format month
            }
        }
    }
}

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun PreviewEmpty() {
    ChoreCreateWhenYear(
        items = generateSelectableItemState(),
        onMonthCheckedChange = { _, _ -> },
    )
}

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun PreviewSome() {
    ChoreCreateWhenYear(
        items = generateSelectableItemState(
            setOf(
                Month.JANUARY,
                Month.FEBRUARY,
                Month.NOVEMBER,
            ),
        ),
        onMonthCheckedChange = { _, _ -> },
    )
}

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun PreviewAll() {
    ChoreCreateWhenYear(
        items = generateSelectableItemState(Month.entries.toSet()),
        onMonthCheckedChange = { _, _ -> },
    )
}
