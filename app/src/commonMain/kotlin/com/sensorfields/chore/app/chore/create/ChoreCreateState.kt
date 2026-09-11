package com.sensorfields.chore.app.chore.create

import com.sensorfields.chore.app.SelectableItemState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month

public data class ChoreCreateState(
    val step: Step,
    val name: String,
    val repeat: Repeat?,
    val date: LocalDate,
    val time: LocalTime,
    val daysOfWeek: ImmutableList<SelectableItemState<DayOfWeek>>,
    val daysOfMonth: ImmutableList<SelectableItemState<Int>>,
    val months: ImmutableList<SelectableItemState<Month>>,
    val isNextButtonEnabled: Boolean,
    val isLoadingVisible: Boolean,
) {
    public enum class Step {
        WHAT,
        WHEN,
        WHEN_DATE,
        WHEN_TIME,
        WHEN_WEEK,
        WHEN_MONTH,
        WHEN_YEAR,
        SUMMARY,
    }

    public enum class Repeat { ONCE, DAILY, WEEKLY, MONTHLY, YEARLY, }

    public companion object {
        public fun initial(): ChoreCreateState = ChoreCreateState(
            step = Step.WHAT,
            name = "",
            repeat = null,
            date = LocalDate.fromEpochDays(0),
            time = LocalTime.fromSecondOfDay(0),
            daysOfWeek = persistentListOf(),
            daysOfMonth = persistentListOf(),
            months = persistentListOf(),
            isNextButtonEnabled = false,
            isLoadingVisible = false,
        )
    }
}
