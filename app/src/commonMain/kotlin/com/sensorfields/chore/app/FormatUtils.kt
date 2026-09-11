package com.sensorfields.chore.app

import com.sensorfields.chore.app.chore.create.ChoreCreateState
import com.sensorfields.chore.domain.models.Error
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.chore_repeat_daily
import com.sensorfields.chore.resources.chore_repeat_monthly
import com.sensorfields.chore.resources.chore_repeat_once
import com.sensorfields.chore.resources.chore_repeat_weekly
import com.sensorfields.chore.resources.chore_repeat_yearly
import com.sensorfields.chore.resources.error_general
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

public suspend fun Error.getMessage(): String = when (this) {
    is Error.General -> getString(Res.string.error_general)
}

public fun LocalDateTime.format(): String = LocalDateTime.Formats.ISO.format(this)
public fun LocalDate.format(): String = LocalDate.Formats.ISO.format(this)
public fun LocalTime.format(): String = LocalTime.Formats.ISO.format(this)

public val ChoreCreateState.Repeat.resource: StringResource
    get() = when (this) {
        ChoreCreateState.Repeat.ONCE -> Res.string.chore_repeat_once
        ChoreCreateState.Repeat.DAILY -> Res.string.chore_repeat_daily
        ChoreCreateState.Repeat.WEEKLY -> Res.string.chore_repeat_weekly
        ChoreCreateState.Repeat.MONTHLY -> Res.string.chore_repeat_monthly
        ChoreCreateState.Repeat.YEARLY -> Res.string.chore_repeat_yearly
    }
