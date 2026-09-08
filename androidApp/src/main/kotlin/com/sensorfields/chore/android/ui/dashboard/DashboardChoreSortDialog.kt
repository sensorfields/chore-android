package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sensorfields.chore.app.dashboard.DashboardState
import com.sensorfields.chore.domain.models.Chore
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.dashboard_chore_sort_ascending
import com.sensorfields.chore.resources.dashboard_chore_sort_date
import com.sensorfields.chore.resources.dashboard_chore_sort_descending
import com.sensorfields.chore.resources.dashboard_chore_sort_name
import com.sensorfields.chore.resources.dashboard_chore_sort_title
import com.sensorfields.chore.theme.HorizontalDivider
import com.sensorfields.chore.theme.Icon
import com.sensorfields.chore.theme.Icons
import com.sensorfields.chore.theme.ListItem
import com.sensorfields.chore.theme.ModalBottomSheet
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.rememberModalBottomSheetState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
fun DashboardChoreSortDialog(
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
        sheetState = sheetState,
    ) {
        ListItem { Text(stringResource(Res.string.dashboard_chore_sort_title)) }
        HorizontalDivider()
        ListItem(
            modifier = Modifier.clickable { onClick(Chore.SortProperty.NAME) },
            leadingContent = {
                AscendingIcon(
                    sort = sort,
                    sortBy = Chore.SortProperty.NAME,
                )
            },
        ) { Text(stringResource(Res.string.dashboard_chore_sort_name)) }
        ListItem(
            modifier = Modifier.clickable { onClick(Chore.SortProperty.DATE) },
            leadingContent = {
                AscendingIcon(
                    sort = sort,
                    sortBy = Chore.SortProperty.DATE,
                )
            },
        ) { Text(stringResource(Res.string.dashboard_chore_sort_date)) }
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
                contentDescription = stringResource(Res.string.dashboard_chore_sort_ascending),
                modifier = modifier,
            )
        }

        sort.sortBy == sortBy -> {
            Icon(
                Icons.ArrowDownward,
                contentDescription = stringResource(Res.string.dashboard_chore_sort_descending),
                modifier = modifier,
            )
        }

        else -> {
            Spacer(modifier = modifier.size(24.dp))
        }
    }
}
