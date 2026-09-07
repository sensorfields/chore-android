package com.sensorfields.chore.android.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun ListItem(
    modifier: Modifier = Modifier,
    overlineContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    androidx.compose.material3.ListItem(
        headlineContent = content,
        modifier = modifier,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
    )
}
