package com.sensorfields.chore.android.ui.chore

import android.content.Context
import android.text.format.DateFormat
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.Instant
import java.util.Date
import javax.inject.Inject

@Reusable
public class FormatChoreDateUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) : (Instant?) -> String? {

    public override operator fun invoke(date: Instant?): String? {
        return date?.let { DateFormat.getDateFormat(context).format(Date(it.toEpochMilli())) }
    }
}
