package com.sensorfields.chore.android.ui.chore.create

import kotlinx.collections.immutable.ImmutableSet
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month

sealed class ChoreCreateState(
    open val isNextButtonEnabled: Boolean = false,
    open val isLoadingVisible: Boolean = false,
) {
    data class What(
        override val isNextButtonEnabled: Boolean = false,
        val name: String = "",
    ) : ChoreCreateState()

    data object When : ChoreCreateState() {
        enum class Repeat { ONCE, DAILY, WEEKLY, MONTHLY, YEARLY, }
    }

    data class WhenDate(
        override val isNextButtonEnabled: Boolean = false,
        val date: LocalDate?,
    ) : ChoreCreateState()

    data class WhenTime(
        override val isNextButtonEnabled: Boolean = false,
        val time: LocalTime?,
    ) : ChoreCreateState()

    data class WhenWeek(
        override val isNextButtonEnabled: Boolean = false,
        val days: ImmutableSet<DayOfWeek>,
    ) : ChoreCreateState()

    data class WhenMonth(
        override val isNextButtonEnabled: Boolean = false,
        val days: ImmutableSet<Int>,
    ) : ChoreCreateState()

    data class WhenYear(
        override val isNextButtonEnabled: Boolean = false,
        val months: ImmutableSet<Month>,
    ) : ChoreCreateState()

    data class Summary(
        val name: String,
        val repeat: When.Repeat,
        val date: LocalDate?,
        val time: LocalTime?,
        val daysOfWeek: ImmutableSet<DayOfWeek>,
        val daysOfMonth: ImmutableSet<Int>,
        val months: ImmutableSet<Month>,
        override val isLoadingVisible: Boolean = false,
    ) : ChoreCreateState(isNextButtonEnabled = true)
}
