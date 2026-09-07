package com.sensorfields.chore.core

public fun logDebug(tag: String? = null, message: () -> String): Unit = logDebug(
    tag = tag ?: "com.sensorfields.chore",
    message = message(),
)

public fun logWarning(tag: String? = null, throwable: () -> Throwable): Unit = logWarning(
    tag = tag ?: "com.sensorfields.chore",
    throwable = throwable(),
)

internal expect fun logDebug(tag: String, message: String)
internal expect fun logWarning(tag: String, throwable: Throwable)
