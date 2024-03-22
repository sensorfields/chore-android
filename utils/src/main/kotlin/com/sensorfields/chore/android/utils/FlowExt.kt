package com.sensorfields.chore.android.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("ComposableNaming")
@Composable
public fun <T> Flow<T>.collectInEffect(collector: FlowCollector<T>) {
    LaunchedEffect(Unit) {
        launch { collect(collector) }
    }
}

@SuppressLint("ComposableNaming")
@Composable
public fun <T> Flow<T>.collectLatestInEffect(action: suspend (value: T) -> Unit) {
    LaunchedEffect(Unit) {
        launch { collectLatest(action) }
    }
}
