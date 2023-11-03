package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DashboardChoreItem(
    state: DashboardState.Chore,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = { Text(state.name) },
        modifier = modifier
    )
}

@Preview
@Composable
private fun Preview() {
    DashboardChoreItem(
        state = DashboardState.Chore(
            id = "one",
            name = "Some Chore that needs to be done"
        )
    )
}
