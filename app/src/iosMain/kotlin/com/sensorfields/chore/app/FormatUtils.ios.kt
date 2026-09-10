package com.sensorfields.chore.app

import com.sensorfields.chore.app.chore.create.ChoreCreateState
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atDate
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import platform.Foundation.NSDate
import platform.Foundation.dateWithTimeIntervalSince1970
import platform.Foundation.timeIntervalSince1970
import kotlin.time.Instant

public fun Instant.toDate(): NSDate = NSDate.dateWithTimeIntervalSince1970(epochSeconds.toDouble())
public fun LocalDateTime.toDate(): NSDate = toInstant(TimeZone.UTC).toDate()
public fun LocalDate.toDate(): NSDate = atTime(hour = 0, minute = 0).toDate()
public fun LocalTime.toDate(): NSDate = atDate(LocalDate.fromEpochDays(0)).toDate()

public fun NSDate.toInstant(): Instant = Instant.fromEpochSeconds(this.timeIntervalSince1970.toLong())
public fun NSDate.toLocalDateTime(): LocalDateTime = toInstant().toLocalDateTime(TimeZone.UTC)
public fun NSDate.toLocalDate(): LocalDate = toLocalDateTime().date
public fun NSDate.toLocalTime(): LocalTime = toLocalDateTime().time

public fun StringResource.format(): String = runBlocking { getString(this@format) }

public fun ChoreCreateState.When.Repeat.format(): String = this.resource.format()
