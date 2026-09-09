package com.sensorfields.chore.android.ui.chore

import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.toInstant
import java.util.Date

@Composable
@ReadOnlyComposable
fun choreDate(date: LocalDateTime): String {
    val context = LocalContext.current
    return DateFormat.getDateFormat(context).format(Date(date.toInstant(UtcOffset.ZERO).toEpochMilliseconds()))
}
