package com.sensorfields.chore.android.ui.theme

import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier

@Composable
public fun ButtonGroup(
    modifier: Modifier = Modifier,
    content: ButtonGroupScope.() -> Unit,
) {
    MultiChoiceSegmentedButtonRow(
        modifier = modifier,
    ) {
        val latestContent = rememberUpdatedState(content)
        val scope by remember {
            derivedStateOf { ButtonGroupScopeWrapper().apply(latestContent.value) }
        }
        scope.items.forEachIndexed { index, item ->
            SegmentedButton(
                checked = item.checked,
                onCheckedChange = item.onCheckedChange,
                shape = SegmentedButtonDefaults.itemShape(index = index, count = scope.items.size),
                enabled = item.enabled,
            ) {
                Text(item.label)
            }
        }
    }
}

public interface ButtonGroupScope {

    public fun toggleableItem(
        checked: Boolean,
        label: String,
        onCheckedChange: (Boolean) -> Unit,
        enabled: Boolean = true,
    )
}

private class ButtonGroupScopeWrapper : ButtonGroupScope {

    val items = mutableListOf<Item>()

    override fun toggleableItem(
        checked: Boolean,
        label: String,
        onCheckedChange: (Boolean) -> Unit,
        enabled: Boolean,
    ) {
        items.add(
            Item(
                checked = checked,
                label = label,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
            )
        )
    }
}

internal data class Item(
    val checked: Boolean,
    val label: String,
    val onCheckedChange: (Boolean) -> Unit,
    val enabled: Boolean,
)
