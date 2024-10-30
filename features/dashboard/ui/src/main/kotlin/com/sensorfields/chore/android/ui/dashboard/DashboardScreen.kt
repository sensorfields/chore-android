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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.ui.collectInEffect
import com.sensorfields.chore.android.ui.dashboard.DashboardAction.ShowChoreCreatedMessage
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.FloatingActionButton
import com.sensorfields.chore.android.ui.theme.Icon
import com.sensorfields.chore.android.ui.theme.IconButton
import com.sensorfields.chore.android.ui.theme.Icons
import com.sensorfields.chore.android.ui.theme.Scaffold
import com.sensorfields.chore.android.ui.theme.SnackbarHost
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.android.ui.theme.TopAppBar
import com.sensorfields.chore.android.ui.theme.rememberSnackbarHostState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun DashboardScreen(
    state: DashboardState,
    actions: Flow<DashboardAction>,
    onChoreSortByClick: (Chore.SortProperty) -> Unit,
    onCreateChoreClick: () -> Unit,
    onChoreClick: (Chore.Id) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val snackbarHostState = rememberSnackbarHostState()
    var isChoreSortDialogVisible by remember { mutableStateOf(false) }

    actions.collectInEffect { action ->
        when (action) {
            is ShowChoreCreatedMessage -> snackbarHostState.showSnackbar(
                message = context.getString(
                    R.string.dashboard_chore_created_message,
                    action.choreName
                )
            )
        }
    }

    if (isChoreSortDialogVisible) {
        DashboardChoreSortDialog(
            sort = state.choreSort,
            onSortByClick = onChoreSortByClick,
            onDismissRequest = { isChoreSortDialogVisible = false }
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.dashboard_title)) },
                actions = {
                    IconButton(onClick = { isChoreSortDialogVisible = true }) {
                        Icon(
                            Icons.Sort,
                            contentDescription = stringResource(R.string.dashboard_sort_button)
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateChoreClick) {
                Icon(
                    Icons.Add,
                    contentDescription = stringResource(R.string.dashboard_chore_create_button)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
        ) {
            items(state.choreItems, key = { it.id.value }) { itemState ->
                DashboardChoreItem(
                    state = itemState,
                    onClick = { onChoreClick(itemState.id) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() = AppTheme {
    DashboardScreen(
        state = DashboardState(),
        actions = emptyFlow(),
        onChoreSortByClick = {},
        onCreateChoreClick = {},
        onChoreClick = {}
    )
}
