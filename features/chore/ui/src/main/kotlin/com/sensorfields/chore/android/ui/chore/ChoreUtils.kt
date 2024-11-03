package com.sensorfields.chore.android.ui.chore

import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import java.time.Instant
import java.util.Date

@Composable
@ReadOnlyComposable
public fun choreDate(date: Instant): String {
    val context = LocalContext.current
    return DateFormat.getDateFormat(context).format(Date(date.toEpochMilli()))
}
