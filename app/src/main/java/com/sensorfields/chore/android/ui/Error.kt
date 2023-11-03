package com.sensorfields.chore.android.ui

import android.content.Context
import com.sensorfields.chore.android.R
import logcat.asLog
import logcat.logcat

fun Context.getErrorMessage(error: Exception): String {
    logcat { error.asLog() }
    return getString(R.string.error_general)
}
