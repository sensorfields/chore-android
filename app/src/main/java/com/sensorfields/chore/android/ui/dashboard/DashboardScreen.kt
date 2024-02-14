package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.R
import com.sensorfields.chore.android.ui.dashboard.DashboardAction.ShowChoreCreatedMessage
import com.sensorfields.chore.android.utils.FlowCollectEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    state: DashboardState,
    actions: Flow<DashboardAction>,
    onChoreSortByClick: (DashboardState.ChoreSortBy) -> Unit,
    onCreateChoreClick: () -> Unit,
    onChoreClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    var isChoreSortDialogVisible by remember { mutableStateOf(false) }

    FlowCollectEffect(actions) { action ->
        when (action) {
            ShowChoreCreatedMessage -> snackbarHostState.showSnackbar(
                message = context.getString(R.string.dashboard_chore_created_message)
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
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.dashboard_title)) },
                actions = {
                    IconButton(onClick = { isChoreSortDialogVisible = true }) {
                        Icon(
                            Icons.AutoMirrored.Default.Sort,
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
                    Icons.Default.Add,
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
            items(state.chores, key = { it.id }) { itemState ->
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
private fun Preview() {
    DashboardScreen(
        state = DashboardState(),
        actions = emptyFlow(),
        onChoreSortByClick = {},
        onCreateChoreClick = {},
        onChoreClick = {}
    )
}
