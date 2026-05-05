package com.sensorfields.chore.android.ui.chore.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sensorfields.chore.android.domain.usecases.CreateChoreUseCase
import com.sensorfields.chore.android.ui.ActionChannel
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.ShowError
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateNavigationAction.Finish
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateState.When.Repeat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.ZoneOffset
import javax.inject.Inject

@HiltViewModel
internal class ChoreCreateViewModel @Inject constructor(
    private val createChoreUseCase: CreateChoreUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<ChoreCreateState>(ChoreCreateState.What())
    val state = _state.asStateFlow()

    private val _navigationAction = ActionChannel<ChoreCreateNavigationAction>()
    val navigationAction = _navigationAction.receiveAsFlow()

    private val _action = ActionChannel<ChoreCreateAction>()
    val action = _action.receiveAsFlow()

    private var name: String = ""
    private var repeat: Repeat = Repeat.ONCE
    private var date: LocalDate? = null
    private var time: LocalTime? = null
    private var daysOfWeek = mutableSetOf<DayOfWeek>()
    private var daysOfMonth = mutableSetOf<Int>()
    private var months = mutableSetOf<Month>()

    fun onNameChange(name: String) {
        this.name = name
        updateState()
    }

    fun onRepeatClick(repeat: Repeat) {
        this.repeat = repeat
        when (repeat) {
            Repeat.ONCE -> {
                _state.update { ChoreCreateState.WhenDate(date = date) }
            }

            Repeat.DAILY -> {
                _state.update { ChoreCreateState.WhenTime(time = time) }
            }

            Repeat.WEEKLY -> {
                _state.update { ChoreCreateState.WhenWeek(days = daysOfWeek.toImmutableSet()) }
            }

            Repeat.MONTHLY -> {
                _state.update { ChoreCreateState.WhenMonth(days = daysOfMonth.toImmutableSet()) }
            }

            Repeat.YEARLY -> {
                _state.update { ChoreCreateState.WhenYear(months = months.toImmutableSet()) }
            }
        }
    }

    fun onDateChange(date: LocalDate?) {
        this.date = date
        updateState()
    }

    fun onTimeChange(time: LocalTime) {
        this.time = time
        updateState()
    }

    fun onDayOfWeekCheckedChange(day: DayOfWeek, checked: Boolean) {
        if (checked) {
            daysOfWeek.add(day)
        } else {
            daysOfWeek.remove(day)
        }
        updateState()
    }

    fun onDayOfMonthCheckedChange(day: Int, checked: Boolean) {
        if (checked) {
            daysOfMonth.add(day)
        } else {
            daysOfMonth.remove(day)
        }
        updateState()
    }

    fun onMonthCheckedChange(month: Month, checked: Boolean) {
        if (checked) {
            months.add(month)
        } else {
            months.remove(month)
        }
        updateState()
    }

    @Suppress("CyclomaticComplexMethod")
    fun onNextClick() {
        when (val state = _state.value) {
            is ChoreCreateState.What -> {
                if (isWhatValid()) {
                    _state.update { ChoreCreateState.When }
                }
            }

            ChoreCreateState.When -> Unit

            is ChoreCreateState.WhenDate -> {
                if (isDateValid()) {
                    _state.update { ChoreCreateState.WhenTime(time = time) }
                }
            }

            is ChoreCreateState.WhenTime -> {
                _state.update {
                    ChoreCreateState.Summary(
                        name = name,
                        repeat = repeat,
                        date = date,
                        time = time,
                        daysOfWeek = daysOfWeek.toImmutableSet(),
                        daysOfMonth = daysOfMonth.toImmutableSet(),
                        months = months.toImmutableSet(),
                    )
                }
            }

            is ChoreCreateState.WhenWeek -> {
                if (isWeekValid()) {
                    _state.update { ChoreCreateState.WhenTime(time = time) }
                }
            }

            is ChoreCreateState.WhenMonth -> {
                if (isMonthValid()) {
                    _state.update { ChoreCreateState.WhenTime(time = time) }
                }
            }

            is ChoreCreateState.WhenYear -> {
                if (isYearValid()) {
                    _state.update { ChoreCreateState.WhenMonth(days = daysOfMonth.toImmutableSet()) }
                }
            }

            is ChoreCreateState.Summary -> viewModelScope.launch {
                val date = date
                val time = time
                if (date != null && time != null) {
                    _state.update { state.copy(isLoadingVisible = true) }
                    createChoreUseCase(
                        name = name,
                        date = date.atTime(time).toInstant(ZoneOffset.UTC),
                    ).onSuccess { chore ->
                        _navigationAction.trySend(Finish(chore = chore))
                    }.onFailure { error ->
                        _action.trySend(ShowError(error = error))
                    }
                }
            }
        }
    }

    private fun updateState() {
        _state.update {
            when (it) {
                is ChoreCreateState.What -> {
                    it.copy(
                        isNextButtonEnabled = isWhatValid(),
                        name = name,
                    )
                }

                ChoreCreateState.When -> it

                is ChoreCreateState.WhenDate -> {
                    it.copy(
                        isNextButtonEnabled = isDateValid(),
                        date = date,
                    )
                }

                is ChoreCreateState.WhenTime -> {
                    it.copy(
                        isNextButtonEnabled = isTimeValid(),
                        time = time,
                    )
                }

                is ChoreCreateState.WhenWeek -> {
                    it.copy(
                        isNextButtonEnabled = isWeekValid(),
                        days = daysOfWeek.toImmutableSet(),
                    )
                }

                is ChoreCreateState.WhenMonth -> {
                    it.copy(
                        isNextButtonEnabled = isMonthValid(),
                        days = daysOfMonth.toImmutableSet(),
                    )
                }

                is ChoreCreateState.WhenYear -> {
                    it.copy(
                        isNextButtonEnabled = isYearValid(),
                        months = months.toImmutableSet(),
                    )
                }

                is ChoreCreateState.Summary -> {
                    it.copy(
                        name = name,
                        repeat = repeat,
                        date = date,
                        time = time,
                        daysOfWeek = daysOfWeek.toImmutableSet(),
                    )
                }
            }
        }
    }

    private fun isWhatValid(): Boolean {
        return name.isNotBlank()
    }

    private fun isDateValid(): Boolean {
        return date != null
    }

    private fun isTimeValid(): Boolean {
        return time != null
    }

    private fun isWeekValid(): Boolean {
        return daysOfWeek.isNotEmpty()
    }

    private fun isMonthValid(): Boolean {
        return daysOfMonth.isNotEmpty()
    }

    private fun isYearValid(): Boolean {
        // TODO check screen
        return months.isNotEmpty()
    }
}
