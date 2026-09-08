package com.sensorfields.chore.core

import android.util.Log

internal actual fun logDebug(tag: String, message: String) {
    Log.d(tag, message)
}

internal actual fun logWarning(tag: String, throwable: Throwable) {
    Log.w(tag, throwable)
}
