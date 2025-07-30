package com.sensorfields.chore.android.ui.theme

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
public fun TopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    androidx.compose.material3.TopAppBar(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        titleHorizontalAlignment = Alignment.CenterHorizontally,
    )
}

@Preview
@Composable
private fun PreviewFull() = AppTheme {
    TopAppBar(
        title = { Text("This is title") },
        subtitle = { Text("Some subtitle") },
        navigationIcon = { CloseButton(onClick = {}) },
        actions = { IconButton(Icons.Add, contentDescription = "Add", onClick = {}) }
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
