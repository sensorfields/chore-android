package com.sensorfields.chore.app

import com.sensorfields.chore.domain.models.Error
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.error_general
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.resources.getString

public suspend fun Error.getMessage(): String = when (this) {
    is Error.General -> getString(Res.string.error_general)
}

public fun LocalDateTime.format(): String = LocalDateTime.Formats.ISO.format(this)
public fun LocalDate.format(): String = LocalDate.Formats.ISO.format(this)
public fun LocalTime.format(): String = LocalTime.Formats.ISO.format(this)
