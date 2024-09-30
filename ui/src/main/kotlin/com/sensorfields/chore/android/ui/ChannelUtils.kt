package com.sensorfields.chore.android.ui

import kotlinx.coroutines.channels.Channel

@Suppress("FunctionName")
public fun <T> ActionChannel(): Channel<T> = Channel(capacity = Channel.UNLIMITED)
