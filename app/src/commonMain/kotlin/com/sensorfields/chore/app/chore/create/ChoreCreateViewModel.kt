package com.sensorfields.chore.app.chore.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sensorfields.chore.app.chore.create.ChoreCreateAction.ShowError
import com.sensorfields.chore.app.chore.create.ChoreCreateNavigationAction.Finish
import com.sensorfields.chore.app.chore.create.ChoreCreateState.When.Repeat
import com.sensorfields.chore.core.ActionChannel
import com.sensorfields.chore.domain.usecases.CreateChoreUseCase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant

@Inject
@ViewModelKey
@ContributesIntoMap(AppScope::class)
public class ChoreCreateViewModel(
    private val createChoreUseCase: CreateChoreUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<ChoreCreateState>(ChoreCreateState.What())
    public val state: StateFlow<ChoreCreateState> = _state.asStateFlow()

    private val _navigationAction = ActionChannel<ChoreCreateNavigationAction>()
    public val navigationAction: Flow<ChoreCreateNavigationAction> = _navigationAction.receiveAsFlow()

    private val _action = ActionChannel<ChoreCreateAction>()
    public val action: Flow<ChoreCreateAction> = _action.receiveAsFlow()

    private var name: String = ""
    private var repeat: Repeat = Repeat.ONCE
    private var date: LocalDate? = null
    private var time: LocalTime? = null
    private var daysOfWeek = mutableSetOf<DayOfWeek>()
    private var daysOfMonth = mutableSetOf<Int>()
    private var months = mutableSetOf<Month>()

    public fun onNameChange(name: String) {
        this.name = name
        updateState()
    }

    public fun onRepeatClick(repeat: Repeat) {
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

    public fun onDateChange(date: LocalDate?) {
        this.date = date
        updateState()
    }

    public fun onTimeChange(time: LocalTime) {
        this.time = time
        updateState()
    }

    public fun onDayOfWeekCheckedChange(day: DayOfWeek, checked: Boolean) {
        if (checked) {
            daysOfWeek.add(day)
        } else {
            daysOfWeek.remove(day)
        }
        updateState()
    }

    public fun onDayOfMonthCheckedChange(day: Int, checked: Boolean) {
        if (checked) {
            daysOfMonth.add(day)
        } else {
            daysOfMonth.remove(day)
        }
        updateState()
    }

    public fun onMonthCheckedChange(month: Month, checked: Boolean) {
        if (checked) {
            months.add(month)
        } else {
            months.remove(month)
        }
        updateState()
    }

    @Suppress("CyclomaticComplexMethod")
    public fun onNextClick() {
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
                    when (val result = createChoreUseCase(
                        name = name,
                        date = date.atTime(time).toInstant(TimeZone.currentSystemDefault()), // TODO TimeZone
                    )) {
                        is CreateChoreUseCase.Result.Success -> {
                            _navigationAction.trySend(Finish(chore = result.chore))
                        }

                        is CreateChoreUseCase.Result.Failure -> {
                            _action.trySend(ShowError(error = result.error))
                        }
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
