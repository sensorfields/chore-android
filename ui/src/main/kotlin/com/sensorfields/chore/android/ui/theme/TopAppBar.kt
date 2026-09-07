package com.sensorfields.chore.android.ui.theme

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun TopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
    )
}

@Preview
@Composable
private fun PreviewFull() = AppTheme {
    TopAppBar(
        title = { Text("This is title") },
        subtitle = { Text("Some subtitle") },
        navigationIcon = { CloseButton(onClick = {}) },
        actions = { IconButton(Icons.Add, contentDescription = "Add", onClick = {}) },
    )
}

@Preview
@Composable
private fun PreviewTitle() = AppTheme {
    TopAppBar(
        title = { Text("This is title") },
    )
}

@Preview
@Composable
private fun PreviewTitleAndUp() = AppTheme {
    TopAppBar(
        title = { Text("This is title") },
        navigationIcon = { UpButton(onClick = {}) },
    )
}
