package com.sensorfields.chore.android.ui.theme

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun ToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    // TODO ToggleButton
//    androidx.compose.material3.ToggleButton(
//        checked = checked,
//        onCheckedChange = onCheckedChange,
//        modifier = modifier,
//        enabled = enabled,
//        content = content,
//    )
}
