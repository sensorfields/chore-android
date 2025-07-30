package com.sensorfields.chore.android.ui.chore.create

import androidx.lifecycle.ViewModel
import com.sensorfields.chore.android.domain.usecases.CreateChoreUseCase
import com.sensorfields.chore.android.ui.ActionChannel
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateState.When.Repeat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
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
    private var days = mutableSetOf<DayOfWeek>()
    private var isLoading: Boolean = false

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
                _state.update { ChoreCreateState.WhenWeek(days = days) }
            }

            Repeat.MONTHLY -> TODO()
            Repeat.YEARLY -> TODO()
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

    fun onDayCheckedChange(day: DayOfWeek, checked: Boolean) {
        if (checked) {
            days.add(day)
        } else {
            days.remove(day)
        }
        updateState()
    }

    fun onNextClick() {
        when (_state.value) {
            is ChoreCreateState.What -> {
                if (isWhatValid()) {
                    _state.update { ChoreCreateState.When }
                }
            }

            ChoreCreateState.When -> Unit

            is ChoreCreateState.WhenDate -> {
                when (repeat) {
                    Repeat.ONCE -> {
                        _state.update { ChoreCreateState.WhenTime(time = time) }
                    }

                    Repeat.DAILY -> TODO()
                    Repeat.WEEKLY -> TODO()
                    Repeat.MONTHLY -> TODO()
                    Repeat.YEARLY -> TODO()
                }
            }

            is ChoreCreateState.WhenTime -> {
                when (repeat) {
                    Repeat.ONCE,
                    Repeat.DAILY,
                    Repeat.WEEKLY -> _state.update {
                        ChoreCreateState.Summary(
                            name = name,
                            repeat = repeat,
                            date = date,
                            time = time,
                            days = days,
                        )
                    }

                    Repeat.MONTHLY -> TODO()
                    Repeat.YEARLY -> TODO()
                }
            }

            is ChoreCreateState.WhenWeek -> {
                if (isWeekValid()) {
                    _state.update { ChoreCreateState.WhenTime(time = time) }
                }
            }

            is ChoreCreateState.Summary -> {
                // TODO save and finish
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
                        days = days.toSet(),
                    )
                }

                is ChoreCreateState.Summary -> {
                    it.copy(
                        name = name,
                        repeat = repeat,
                        date = date,
                        time = time,
                        days = days,
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
        return days.isNotEmpty()
    }
}
