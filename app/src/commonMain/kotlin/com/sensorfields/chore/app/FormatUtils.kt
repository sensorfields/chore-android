package com.sensorfields.chore.app

import com.sensorfields.chore.app.chore.create.ChoreCreateState
import com.sensorfields.chore.app.dashboard.DashboardState
import com.sensorfields.chore.domain.models.Chore
import com.sensorfields.chore.domain.models.Error
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.chore_repeat_daily
import com.sensorfields.chore.resources.chore_repeat_monthly
import com.sensorfields.chore.resources.chore_repeat_once
import com.sensorfields.chore.resources.chore_repeat_weekly
import com.sensorfields.chore.resources.chore_repeat_yearly
import com.sensorfields.chore.resources.dashboard_chore_sort_date
import com.sensorfields.chore.resources.dashboard_chore_sort_name
import com.sensorfields.chore.resources.day_of_month_friday
import com.sensorfields.chore.resources.day_of_month_monday
import com.sensorfields.chore.resources.day_of_month_saturday
import com.sensorfields.chore.resources.day_of_month_sunday
import com.sensorfields.chore.resources.day_of_month_thursday
import com.sensorfields.chore.resources.day_of_month_tuesday
import com.sensorfields.chore.resources.day_of_month_wednesday
import com.sensorfields.chore.resources.error_general
import com.sensorfields.chore.resources.month_april
import com.sensorfields.chore.resources.month_august
import com.sensorfields.chore.resources.month_december
import com.sensorfields.chore.resources.month_february
import com.sensorfields.chore.resources.month_january
import com.sensorfields.chore.resources.month_july
import com.sensorfields.chore.resources.month_june
import com.sensorfields.chore.resources.month_march
import com.sensorfields.chore.resources.month_may
import com.sensorfields.chore.resources.month_november
import com.sensorfields.chore.resources.month_october
import com.sensorfields.chore.resources.month_september
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import kotlinx.datetime.format.Padding
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

public suspend fun Error.getMessage(): String = when (this) {
    is Error.General -> getString(Res.string.error_general)
}

public fun LocalDateTime.format(): String = LocalDateTime.Formats.ISO.format(this)
public fun LocalDate.format(): String = LocalDate.Formats.ISO.format(this)
public fun LocalTime.format(): String = LocalTime.Formats.ISO.format(this)

public fun Int.formatDayOfMonth(padding: Boolean = false): String = LocalDate.Format {
    day(padding = if (padding) Padding.ZERO else Padding.NONE)
}.format(LocalDate(year = 0, month = 1, day = this))

internal val DayOfWeek.resource: StringResource
    get() = when (this) {
        DayOfWeek.MONDAY -> Res.string.day_of_month_monday
        DayOfWeek.TUESDAY -> Res.string.day_of_month_tuesday
        DayOfWeek.WEDNESDAY -> Res.string.day_of_month_wednesday
        DayOfWeek.THURSDAY -> Res.string.day_of_month_thursday
        DayOfWeek.FRIDAY -> Res.string.day_of_month_friday
        DayOfWeek.SATURDAY -> Res.string.day_of_month_saturday
        DayOfWeek.SUNDAY -> Res.string.day_of_month_sunday
    }

internal val Month.resource: StringResource
    get() = when (this) {
        Month.JANUARY -> Res.string.month_january
        Month.FEBRUARY -> Res.string.month_february
        Month.MARCH -> Res.string.month_march
        Month.APRIL -> Res.string.month_april
        Month.MAY -> Res.string.month_may
        Month.JUNE -> Res.string.month_june
        Month.JULY -> Res.string.month_july
        Month.AUGUST -> Res.string.month_august
        Month.SEPTEMBER -> Res.string.month_september
        Month.OCTOBER -> Res.string.month_october
        Month.NOVEMBER -> Res.string.month_november
        Month.DECEMBER -> Res.string.month_december
    }

internal val ChoreCreateState.Repeat.resource: StringResource
    get() = when (this) {
        ChoreCreateState.Repeat.ONCE -> Res.string.chore_repeat_once
        ChoreCreateState.Repeat.DAILY -> Res.string.chore_repeat_daily
        ChoreCreateState.Repeat.WEEKLY -> Res.string.chore_repeat_weekly
        ChoreCreateState.Repeat.MONTHLY -> Res.string.chore_repeat_monthly
        ChoreCreateState.Repeat.YEARLY -> Res.string.chore_repeat_yearly
    }

internal val Chore.SortProperty.resource: StringResource
    get() = when (this) {
        Chore.SortProperty.NAME -> Res.string.dashboard_chore_sort_name
        Chore.SortProperty.DATE -> Res.string.dashboard_chore_sort_date
    }
