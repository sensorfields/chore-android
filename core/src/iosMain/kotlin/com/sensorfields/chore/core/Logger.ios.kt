package com.sensorfields.chore.core

import platform.Foundation.NSLog

internal actual fun logDebug(tag: String, message: String) {
    NSLog("DEBUG: $message")
}

internal actual fun logWarning(tag: String, throwable: Throwable) {
    NSLog("WARN: ${throwable.message}")
}
