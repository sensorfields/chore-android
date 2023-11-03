package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DashboardScreen(
    state: DashboardState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(state.chores, key = { it.id }) { itemState ->
            DashboardChoreItem(state = itemState)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DashboardScreen(
        state = DashboardState()
    )
}
