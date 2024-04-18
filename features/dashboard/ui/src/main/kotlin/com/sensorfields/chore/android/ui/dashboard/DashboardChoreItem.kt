package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.domain.models.Chore

@Composable
internal fun DashboardChoreItem(
    state: DashboardState.ChoreItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = { Text(state.name) },
        modifier = modifier.clickable(onClick = onClick),
        supportingContent = state.date?.let { { Text(it) } }
    )
}

@Preview
@Composable
private fun PreviewFull() {
    DashboardChoreItem(
        state = DashboardState.ChoreItem(
            id = Chore.Id("one"),
            name = "Some Chore that needs to be done",
            date = "2/13/88"
        ),
        onClick = {}
    )
}

@Preview
@Composable
private fun PreviewMin() {
    DashboardChoreItem(
        state = DashboardState.ChoreItem(
            id = Chore.Id("one"),
            name = "Some Chore that needs to be done",
            date = null
        ),
        onClick = {}
    )
}
