package com.sensorfields.chore.theme

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
public fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit,
) {
    androidx.compose.material3.Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        content = content,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewEnabled() = AppTheme {
    Button(onClick = {}) { Text("Click me") }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDisabled() = AppTheme {
    Button(
        onClick = {},
        enabled = false,
    ) { Text("Click me") }
}
