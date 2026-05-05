package com.sensorfields.chore.android.ui

import android.content.res.Resources
import logcat.asLog
import logcat.logcat

public fun Resources.getErrorMessage(error: Throwable): String {
    logcat { error.asLog() }
    return getString(R.string.error_general)
}
