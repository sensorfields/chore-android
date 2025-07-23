package com.sensorfields.chore.android.ui.chore.create

import java.time.Instant

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

    data object WhenTime : ChoreCreateState()
}
