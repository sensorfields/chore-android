package com.sensorfields.chore.android.utils

import android.content.Context
import android.text.format.DateFormat
import java.time.Instant
import java.util.Date

public fun Context.formatDate(instant: Instant): String {
    return DateFormat.getDateFormat(this).format(Date(instant.toEpochMilli()))
}
