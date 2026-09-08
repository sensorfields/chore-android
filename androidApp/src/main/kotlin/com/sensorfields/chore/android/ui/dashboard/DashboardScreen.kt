package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewWrapper
import com.sensorfields.chore.app.dashboard.DashboardAction
import com.sensorfields.chore.app.dashboard.DashboardAction.ShowChoreCreatedMessage
import com.sensorfields.chore.app.dashboard.DashboardState
import com.sensorfields.chore.core.collectInEffect
import com.sensorfields.chore.domain.models.Chore
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.dashboard_chore_create_button
import com.sensorfields.chore.resources.dashboard_chore_created_message
import com.sensorfields.chore.resources.dashboard_sort_button
import com.sensorfields.chore.resources.dashboard_title
import com.sensorfields.chore.theme.AppPreviewWrapper
import com.sensorfields.chore.theme.FloatingActionButton
import com.sensorfields.chore.theme.Icon
import com.sensorfields.chore.theme.IconButton
import com.sensorfields.chore.theme.Icons
import com.sensorfields.chore.theme.Scaffold
import com.sensorfields.chore.theme.SnackbarHost
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.TopAppBar
import com.sensorfields.chore.theme.rememberSnackbarHostState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

@Composable
fun DashboardScreen(
    state: DashboardState,
    actions: Flow<DashboardAction>,
    onChoreSortByClick: (Chore.SortProperty) -> Unit,
    onCreateChoreClick: () -> Unit,
    onChoreClick: (Chore.Id) -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = rememberSnackbarHostState()
    var isChoreSortDialogVisible by remember { mutableStateOf(false) }

    actions.collectInEffect { action ->
        when (action) {
            is ShowChoreCreatedMessage -> snackbarHostState.showSnackbar(
                message = getString(
                    Res.string.dashboard_chore_created_message,
                    action.choreName,
                ),
            )
        }
    }

    if (isChoreSortDialogVisible) {
        DashboardChoreSortDialog(
            sort = state.choreSort,
            onSortByClick = onChoreSortByClick,
            onDismissRequest = { isChoreSortDialogVisible = false },
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.dashboard_title)) },
                actions = {
                    IconButton(
                        Icons.Sort,
                        contentDescription = stringResource(Res.string.dashboard_sort_button),
                        onClick = { isChoreSortDialogVisible = true },
                    )
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateChoreClick) {
                Icon(
                    Icons.Add,
                    contentDescription = stringResource(Res.string.dashboard_chore_create_button),
                )
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding),
        ) {
            items(state.choreItems, key = { it.id.value }) { itemState ->
                DashboardChoreItem(
                    state = itemState,
                    onClick = { onChoreClick(itemState.id) },
                )
            }
        }
    }
}

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun Preview() {
    DashboardScreen(
        state = DashboardState(),
        actions = emptyFlow(),
        onChoreSortByClick = {},
        onCreateChoreClick = {},
        onChoreClick = {},
    )
}
