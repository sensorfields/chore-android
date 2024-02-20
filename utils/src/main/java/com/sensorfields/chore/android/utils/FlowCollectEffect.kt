package com.sensorfields.chore.android.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
public fun <T> FlowCollectEffect(flow: Flow<T>?, collector: FlowCollector<T>) {
    if (flow == null) return
    LaunchedEffect(Unit) {
        launch { flow.collect(collector) }
    }
}

@Composable
public fun <T> FlowCollectLatestEffect(flow: Flow<T>, action: suspend (value: T) -> Unit) {
    LaunchedEffect(Unit) {
        launch { flow.collectLatest(action) }
    }
}
