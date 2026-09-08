package com.sensorfields.chore.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

public class SnackBarState internal constructor(
    internal val hostState: androidx.compose.material3.SnackbarHostState,
) {
    public suspend fun show(message: String) {
        hostState.showSnackbar(message = message)
    }
}

@Composable
public fun rememberSnackBarState(): SnackBarState {
    return remember {
        SnackBarState(hostState = androidx.compose.material3.SnackbarHostState())
    }
}

@Composable
internal fun SnackBarHost(
    state: SnackBarState,
    modifier: Modifier = Modifier,
) {
    androidx.compose.material3.SnackbarHost(
        hostState = state.hostState,
        modifier = modifier,
    )
}
