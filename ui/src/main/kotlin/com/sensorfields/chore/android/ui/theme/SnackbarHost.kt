package com.sensorfields.chore.android.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
public fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    androidx.compose.material3.SnackbarHost(
        hostState = hostState.hostState,
        modifier = modifier,
    )
}

public class SnackbarHostState internal constructor(
    internal val hostState: androidx.compose.material3.SnackbarHostState,
) {
    public suspend fun showSnackbar(message: String) {
        hostState.showSnackbar(message = message)
    }
}

@Composable
public fun rememberSnackbarHostState(): SnackbarHostState {
    return remember {
        SnackbarHostState(hostState = androidx.compose.material3.SnackbarHostState())
    }
}
