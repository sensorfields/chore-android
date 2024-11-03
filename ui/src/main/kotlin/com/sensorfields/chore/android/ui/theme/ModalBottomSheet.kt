package com.sensorfields.chore.android.ui.theme

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun ModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable ColumnScope.() -> Unit,
) {
    androidx.compose.material3.ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState.sheetState,
        content = content,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
public class SheetState internal constructor(
    internal val sheetState: androidx.compose.material3.SheetState,
) {
    public suspend fun hide() {
        sheetState.hide()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun rememberModalBottomSheetState(): SheetState {
    val sheetState = androidx.compose.material3.rememberModalBottomSheetState()
    return remember { SheetState(sheetState = sheetState) }
}
