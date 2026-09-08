package com.sensorfields.chore.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Suppress("FunctionName")
public fun <T> ActionChannel(): Channel<T> = Channel(capacity = Channel.UNLIMITED)

@Suppress("ComposableNaming")
@Composable
public fun <T> Flow<T>.collectInEffect(collector: FlowCollector<T>) {
    LaunchedEffect(Unit) {
        launch { collect(collector) }
    }
}

@Suppress("ComposableNaming")
@Composable
public fun <T> Flow<T>.collectLatestInEffect(action: suspend (value: T) -> Unit) {
    LaunchedEffect(Unit) {
        launch { collectLatest(action) }
    }
}
