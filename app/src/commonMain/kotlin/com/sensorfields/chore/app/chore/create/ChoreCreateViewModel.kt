package com.sensorfields.chore.app.chore.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sensorfields.chore.app.chore.create.ChoreCreateAction.Finish
import com.sensorfields.chore.app.chore.create.ChoreCreateAction.ShowError
import com.sensorfields.chore.app.chore.create.ChoreCreateState.Repeat
import com.sensorfields.chore.app.chore.create.ChoreCreateState.Step
import com.sensorfields.chore.app.generateSelectableItemState
import com.sensorfields.chore.core.ActionChannel
import com.sensorfields.chore.core.AppConfig
import com.sensorfields.chore.core.logDebug
import com.sensorfields.chore.domain.usecases.CreateChoreUseCase
import com.sensorfields.chore.domain.usecases.GetLocalDateTimeUseCase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metrox.viewmodel.ViewModelKey
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

@Inject
@ViewModelKey
@ContributesIntoMap(AppScope::class)
public class ChoreCreateViewModel(
    getLocalDateTimeUseCase: GetLocalDateTimeUseCase,
    private val createChoreUseCase: CreateChoreUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ChoreCreateState.initial())
    public val state: StateFlow<ChoreCreateState> = _state.asStateFlow()

    private val _actions = ActionChannel<ChoreCreateAction>()
    public val actions: Flow<ChoreCreateAction> = _actions.receiveAsFlow()

    private var step: Step = Step.WHAT
    private var name: String = ""
    private var repeat: Repeat = Repeat.ONCE
    private var date: LocalDate = getLocalDateTimeUseCase().date
    private var time: LocalTime = getLocalDateTimeUseCase().time
    private var daysOfWeek = mutableSetOf<DayOfWeek>()
    private var daysOfMonth = mutableSetOf<Int>()
    private var months = mutableSetOf<Month>()
    private var createInProgress: Boolean = false

    init {
        logDebug { "AAAAAAAA LOL INIT CREATE VM" }
    }

    public fun onNameChange(name: String) {
        this.name = name
        updateState()
    }

    public fun onRepeatClick(repeat: Repeat) {
        this.repeat = repeat
        step = when (repeat) {
            Repeat.ONCE -> Step.WHEN_DATE
            Repeat.DAILY -> Step.WHEN_TIME
            Repeat.WEEKLY -> Step.WHEN_WEEK
            Repeat.MONTHLY -> Step.WHEN_MONTH
            Repeat.YEARLY -> Step.WHEN_YEAR
        }
        updateState()
    }

    public fun onDateChange(date: LocalDate?) {
        val date = date ?: return
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
        when (step) {
            Step.WHAT -> {
                if (isWhatValid()) {
                    step = Step.WHEN
                    updateState()
                }
            }

            Step.WHEN -> Unit

            Step.WHEN_DATE -> {
                step = Step.WHEN_TIME
                updateState()
            }

            Step.WHEN_TIME -> {
                step = Step.SUMMARY
                updateState()
            }

            Step.WHEN_WEEK -> {
                if (isWeekValid()) {
                    step = Step.WHEN_TIME
                    updateState()
                }
            }

            Step.WHEN_MONTH -> {
                if (isMonthValid()) {
                    step = Step.WHEN_TIME
                    updateState()
                }
            }

            Step.WHEN_YEAR -> {
                if (isYearValid()) {
                    step = Step.WHEN_MONTH
                    updateState()
                }
            }

            Step.SUMMARY -> viewModelScope.launch {
                createInProgress = true
                updateState()
                when (val result = createChoreUseCase(name = name, date = date, time = time)) {
                    is CreateChoreUseCase.Result.Success -> {
                        _actions.trySend(Finish(chore = result.chore))
                    }

                    is CreateChoreUseCase.Result.Failure -> {
                        _actions.trySend(ShowError(error = result.error))
                    }
                }
            }
        }
    }

    private fun updateState() {
        _state.update {
            ChoreCreateState(
                step = step,
                name = name,
                repeat = repeat,
                date = date,
                time = time,
                daysOfWeek = generateSelectableItemState(daysOfWeek),
                daysOfMonth = generateSelectableItemState(range = AppConfig.DAY_OF_MONTH_RANGE, selected = daysOfMonth),
                months = generateSelectableItemState(months),
                isNextButtonEnabled = isNextButtonEnabled(),
                isLoadingVisible = createInProgress,
            )
        }
    }

    private fun isNextButtonEnabled(): Boolean {
        return when (step) {
            Step.WHAT -> isWhatValid()
            Step.WHEN -> false
            Step.WHEN_DATE -> true
            Step.WHEN_TIME -> true
            Step.WHEN_WEEK -> isWeekValid()
            Step.WHEN_MONTH -> isMonthValid()
            Step.WHEN_YEAR -> isYearValid()
            Step.SUMMARY -> true
        }
    }

    private fun isWhatValid(): Boolean {
        return name.isNotBlank()
    }

    private fun isWeekValid(): Boolean {
        return daysOfWeek.isNotEmpty()
    }

    private fun isMonthValid(): Boolean {
        return daysOfMonth.isNotEmpty()
    }

    private fun isYearValid(): Boolean {
        return months.isNotEmpty()
    }
}
