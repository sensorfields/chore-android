package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.ui.chore.choreDate
import com.sensorfields.chore.android.ui.theme.ListItem
import com.sensorfields.chore.android.ui.theme.Text
import java.time.Instant

@Composable
internal fun DashboardChoreItem(
    state: DashboardState.ChoreItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = { Text(state.name) },
        modifier = modifier.clickable(onClick = onClick),
        supportingContent = state.date?.let { { Text(choreDate(it)) } }
    )
}

@Preview
@Composable
private fun PreviewFull() {
    DashboardChoreItem(
        state = DashboardState.ChoreItem(
            id = Chore.Id("one"),
            name = "Some Chore that needs to be done",
            date = Instant.parse("1988-02-13T13:30:00Z"),
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
