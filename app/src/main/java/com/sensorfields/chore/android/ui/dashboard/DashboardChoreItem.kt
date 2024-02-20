package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.domain.model.Chore
import com.sensorfields.chore.android.utils.formatDate
import java.time.Instant

@Composable
fun DashboardChoreItem(
    state: DashboardState.ChoreItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    ListItem(
        headlineContent = { Text(state.name) },
        modifier = modifier.clickable(onClick = onClick),
        supportingContent = { state.date?.let { Text(context.formatDate(it)) } }
    )
}

@Preview
@Composable
private fun Preview() {
    DashboardChoreItem(
        state = DashboardState.ChoreItem(
            id = Chore.Id("one"),
            name = "Some Chore that needs to be done",
            date = Instant.parse("1988-02-13T13:00:00Z")
        ),
        onClick = {}
    )
}
