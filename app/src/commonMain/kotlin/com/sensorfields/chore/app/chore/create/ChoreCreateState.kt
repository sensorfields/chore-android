package com.sensorfields.chore.app.chore.create

import kotlinx.collections.immutable.ImmutableSet
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month

public sealed class ChoreCreateState(
    public open val isNextButtonEnabled: Boolean = false,
    public open val isLoadingVisible: Boolean = false,
) {
    public data class What(
        override val isNextButtonEnabled: Boolean = false,
        val name: String = "",
    ) : ChoreCreateState()

    public data object When : ChoreCreateState() {
        public enum class Repeat { ONCE, DAILY, WEEKLY, MONTHLY, YEARLY, }
    }

    public data class WhenDate(
        override val isNextButtonEnabled: Boolean = false,
        val date: LocalDate?,
    ) : ChoreCreateState()

    public data class WhenTime(
        override val isNextButtonEnabled: Boolean = false,
        val time: LocalTime?,
    ) : ChoreCreateState()

    public data class WhenWeek(
        override val isNextButtonEnabled: Boolean = false,
        val days: ImmutableSet<DayOfWeek>,
    ) : ChoreCreateState()

    public data class WhenMonth(
        override val isNextButtonEnabled: Boolean = false,
        val days: ImmutableSet<Int>,
    ) : ChoreCreateState()

    public data class WhenYear(
        override val isNextButtonEnabled: Boolean = false,
        val months: ImmutableSet<Month>,
    ) : ChoreCreateState()

    public data class Summary(
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
