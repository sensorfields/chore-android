package com.sensorfields.chore.android.utils

import android.content.Context
import logcat.asLog
import logcat.logcat

public fun Context.getErrorMessage(error: Throwable): String {
    logcat { error.asLog() }
    return getString(R.string.error_general)
}
