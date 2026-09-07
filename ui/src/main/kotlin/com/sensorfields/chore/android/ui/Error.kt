package com.sensorfields.chore.android.ui

import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.error_general
import logcat.asLog
import logcat.logcat
import org.jetbrains.compose.resources.getString

public suspend fun Throwable.toErrorMessage(): String {
    logcat(tag = "Error") { asLog() }
    return getString(Res.string.error_general)
}
