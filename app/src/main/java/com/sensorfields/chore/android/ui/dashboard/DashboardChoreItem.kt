package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DashboardChoreItem(
    state: DashboardState.Chore,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = { Text(state.name) },
        modifier = modifier
            .clickable(onClick = onClick)
    )
}

@Preview
@Composable
private fun Preview() {
    DashboardChoreItem(
        state = DashboardState.Chore(
            id = "one",
            name = "Some Chore that needs to be done"
        ),
        onClick = {}
    )
}
