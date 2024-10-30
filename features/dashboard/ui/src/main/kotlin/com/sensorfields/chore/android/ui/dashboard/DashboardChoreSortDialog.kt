package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.ui.theme.HorizontalDivider
import com.sensorfields.chore.android.ui.theme.Icon
import com.sensorfields.chore.android.ui.theme.Icons
import com.sensorfields.chore.android.ui.theme.ListItem
import com.sensorfields.chore.android.ui.theme.ModalBottomSheet
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.android.ui.theme.rememberModalBottomSheetState
import kotlinx.coroutines.launch

@Composable
internal fun DashboardChoreSortDialog(
    sort: DashboardState.ChoreSort,
    onSortByClick: (Chore.SortProperty) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    fun onClick(sortBy: Chore.SortProperty) {
        coroutineScope.launch {
            onSortByClick(sortBy)
            sheetState.hide()
            onDismissRequest()
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState
    ) {
        ListItem(
            headlineContent = { Text(stringResource(R.string.dashboard_chore_sort_title)) }
        )
        HorizontalDivider()
        ListItem(
            headlineContent = { Text(stringResource(R.string.dashboard_chore_sort_name)) },
            modifier = Modifier.clickable { onClick(Chore.SortProperty.NAME) },
            leadingContent = {
                AscendingIcon(
                    sort = sort,
                    sortBy = Chore.SortProperty.NAME
                )
            }
        )
        ListItem(
            headlineContent = { Text(stringResource(R.string.dashboard_chore_sort_date)) },
            modifier = Modifier.clickable { onClick(Chore.SortProperty.DATE) },
            leadingContent = {
                AscendingIcon(
                    sort = sort,
                    sortBy = Chore.SortProperty.DATE
                )
            }
        )
    }
}

@Composable
private fun AscendingIcon(
    sort: DashboardState.ChoreSort,
    sortBy: Chore.SortProperty,
    modifier: Modifier = Modifier,
) {
    when {
        sort.sortBy == sortBy && sort.isAscending -> {
            Icon(
                Icons.ArrowUpward,
                contentDescription = stringResource(R.string.dashboard_chore_sort_ascending),
                modifier = modifier
            )
        }

        sort.sortBy == sortBy -> {
            Icon(
                Icons.ArrowDownward,
                contentDescription = stringResource(R.string.dashboard_chore_sort_descending),
                modifier = modifier
            )
        }

        else -> {
            Spacer(modifier = modifier.size(24.dp))
        }
    }
}
