package com.sensorfields.chore.android.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun <T> FlowCollectEffect(flow: Flow<T>, collector: FlowCollector<T>) {
    LaunchedEffect(Unit) {
        launch { flow.collect(collector) }
    }
}

@Composable
fun <T> FlowCollectLatestEffect(flow: Flow<T>, action: suspend (value: T) -> Unit) {
    LaunchedEffect(Unit) {
        launch { flow.collectLatest(action) }
    }
}
