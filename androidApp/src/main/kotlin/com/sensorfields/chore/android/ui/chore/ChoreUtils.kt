package com.sensorfields.chore.android.ui.chore

import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import java.util.Date
import kotlin.time.Instant

@Composable
@ReadOnlyComposable
fun choreDate(date: Instant): String {
    val context = LocalContext.current
    return DateFormat.getDateFormat(context).format(Date(date.toEpochMilliseconds()))
}
