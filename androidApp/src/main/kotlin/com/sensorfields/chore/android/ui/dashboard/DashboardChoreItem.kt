package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewWrapper
import com.sensorfields.chore.android.ui.chore.choreDate
import com.sensorfields.chore.app.dashboard.DashboardState
import com.sensorfields.chore.domain.models.Chore
import com.sensorfields.chore.theme.AppPreviewWrapper
import com.sensorfields.chore.theme.ListItem
import com.sensorfields.chore.theme.Text
import kotlinx.datetime.LocalDateTime

@Composable
fun DashboardChoreItem(
    state: DashboardState.ChoreItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier.clickable(onClick = onClick),
        supportingContent = { Text(choreDate(state.date)) },
    ) {
        Text(state.name)
    }
}

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun Preview() {
    DashboardChoreItem(
        state = DashboardState.ChoreItem(
            id = Chore.Id("one"),
            name = "Some Chore that needs to be done",
            date = LocalDateTime.parse("1988-02-13T13:30:00"),
        ),
        onClick = {},
    )
}
