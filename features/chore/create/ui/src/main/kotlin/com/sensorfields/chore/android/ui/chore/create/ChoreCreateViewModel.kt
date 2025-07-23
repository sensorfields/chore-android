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
import java.time.Instant
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
    private var date: Instant? = null
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
                _state.update { ChoreCreateState.WhenTime }
            }

            Repeat.WEEKLY -> TODO()
            Repeat.MONTHLY -> TODO()
            Repeat.YEARLY -> TODO()
        }
    }

    fun onDateChange(date: Instant?) {
        this.date = date
        updateState()
    }

    fun onNextClick() {
        when (_state.value) {
            is ChoreCreateState.What -> {
                if (isWhatValid()) {
                    _state.update { ChoreCreateState.When }
                }
            }

            ChoreCreateState.When -> TODO()

            is ChoreCreateState.WhenDate -> {
                when (repeat) {
                    Repeat.ONCE -> {
                        _state.update { ChoreCreateState.WhenTime }
                    }

                    Repeat.DAILY -> TODO()
                    Repeat.WEEKLY -> TODO()
                    Repeat.MONTHLY -> TODO()
                    Repeat.YEARLY -> TODO()
                }
            }

            ChoreCreateState.WhenTime -> TODO()
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

                ChoreCreateState.WhenTime -> it
            }
        }
    }

    private fun isWhatValid(): Boolean {
        return name.isNotBlank()
    }

    private fun isDateValid(): Boolean {
        return date != null
    }
}
