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
import com.sensorfields.chore.app.dashboard.DashboardState
import com.sensorfields.chore.domain.models.Chore
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.dashboard_chore_create_button
import com.sensorfields.chore.resources.dashboard_sort_button
import com.sensorfields.chore.resources.dashboard_title
import com.sensorfields.chore.theme.AppPreviewWrapper
import com.sensorfields.chore.theme.FloatingActionButton
import com.sensorfields.chore.theme.Icon
import com.sensorfields.chore.theme.IconButton
import com.sensorfields.chore.theme.Icons
import com.sensorfields.chore.theme.Scaffold
import com.sensorfields.chore.theme.SnackBarState
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.TopAppBar
import com.sensorfields.chore.theme.rememberSnackBarState
import org.jetbrains.compose.resources.stringResource

@Composable
fun DashboardScreen(
    state: DashboardState,
    onChoreSortByClick: (Chore.SortProperty) -> Unit,
    onCreateChoreClick: () -> Unit,
    onChoreClick: (Chore.Id) -> Unit,
    modifier: Modifier = Modifier,
    snackBarState: SnackBarState = rememberSnackBarState(),
) {
    var isChoreSortDialogVisible by remember { mutableStateOf(false) }

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
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateChoreClick) {
                Icon(
                    Icons.Add,
                    contentDescription = stringResource(Res.string.dashboard_chore_create_button),
                )
            }
        },
        snackBarState = snackBarState,
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
        onChoreSortByClick = {},
        onCreateChoreClick = {},
        onChoreClick = {},
    )
}
