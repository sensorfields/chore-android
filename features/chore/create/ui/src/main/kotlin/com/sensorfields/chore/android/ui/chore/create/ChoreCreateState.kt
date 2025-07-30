package com.sensorfields.chore.android.ui.chore.create

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

internal sealed class ChoreCreateState(
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
        val days: Set<DayOfWeek>,
    ) : ChoreCreateState()

    data class Summary(
        val name: String,
        val repeat: When.Repeat,
        val date: LocalDate?,
        val time: LocalTime?,
    ) : ChoreCreateState(isNextButtonEnabled = true)
}
