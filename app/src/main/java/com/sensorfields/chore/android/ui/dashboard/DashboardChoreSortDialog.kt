package com.sensorfields.chore.android.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sensorfields.chore.android.R
import com.sensorfields.chore.android.domain.model.Chore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
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
                Icons.Default.ArrowUpward,
                contentDescription = stringResource(R.string.dashboard_chore_sort_ascending),
                modifier = modifier
            )
        }

        sort.sortBy == sortBy -> {
            Icon(
                Icons.Default.ArrowDownward,
                contentDescription = stringResource(R.string.dashboard_chore_sort_descending),
                modifier = modifier
            )
        }

        else -> {
            Spacer(modifier = modifier.size(24.dp))
        }
    }
}
