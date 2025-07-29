package com.sensorfields.chore.android.ui.chore.create

import java.time.Instant
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
        val date: Instant?,
    ) : ChoreCreateState()

    data class WhenTime(
        override val isNextButtonEnabled: Boolean = false,
        val time: LocalTime?,
    ) : ChoreCreateState()

    data class Summary(
        val name: String,
        val repeat: When.Repeat,
        val date: Instant?,
        val time: LocalTime?,
    ) : ChoreCreateState(isNextButtonEnabled = true)
}
