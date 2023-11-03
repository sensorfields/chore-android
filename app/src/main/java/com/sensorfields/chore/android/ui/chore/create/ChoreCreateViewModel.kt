package com.sensorfields.chore.android.ui.chore.create

import androidx.lifecycle.ViewModel
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.NavigateToWhen
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.NavigateToWhere
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChoreCreateViewModel @Inject constructor(
) : ViewModel() {

    private val _state = MutableStateFlow(ChoreCreateState())
    val state = _state.asStateFlow()

    private val _action = Channel<ChoreCreateAction>(capacity = Channel.UNLIMITED)
    val action = _action.receiveAsFlow()

    private var screen: Screen = Screen.WHAT
    private var name: String = ""
    private var date: Long? = null

    fun onScreenChange(screen: Screen) {
        this.screen = screen
        updateState()
    }

    fun onNameChange(name: String) {
        this.name = name
        updateState()
    }

    fun onDateChange(date: Long?) {
        this.date = date
        updateState()
    }

    fun onNextClick() {
        when (screen) {
            Screen.WHAT -> {
                if (name.isNotEmpty()) {
                    _action.trySend(NavigateToWhen)
                }
            }

            Screen.WHEN -> {
                if (date != null) {
                    _action.trySend(NavigateToWhere)
                }
            }

            Screen.WHERE -> {
                // TODO create chore and finish flow
            }
        }
    }

    private fun updateState() {
        val isNextButtonEnabled = when (screen) {
            Screen.WHAT -> name.isNotBlank()
            Screen.WHEN -> date != null
            Screen.WHERE -> true
        }

        _state.update {
            it.copy(
                name = name,
                date = date,
                isNextButtonEnabled = isNextButtonEnabled
            )
        }
    }
}
