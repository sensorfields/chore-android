package com.sensorfields.chore.android.ui.chore.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sensorfields.chore.android.domain.usecases.CreateChoreUseCase
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.Finish
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.NavigateToWhen
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.NavigateToWhere
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.ShowError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class ChoreCreateViewModel @Inject constructor(
    private val createChoreUseCase: CreateChoreUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ChoreCreateState())
    val state = _state.asStateFlow()

    private val _action = Channel<ChoreCreateAction>(capacity = Channel.UNLIMITED)
    val action = _action.receiveAsFlow()

    private var screen: Screen = Screen.WHAT
    private var name: String = ""
    private var date: Long? = null
    private var isLoading: Boolean = false

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

            Screen.WHERE -> viewModelScope.launch {
                isLoading = true
                updateState()

                createChoreUseCase(
                    name = name,
                    date = date?.let { Instant.ofEpochMilli(it) }
                ).onSuccess {
                    _action.trySend(Finish)
                }.onFailure {
                    isLoading = false
                    updateState()
                    _action.trySend(ShowError(it))
                }
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
                isNextButtonEnabled = isNextButtonEnabled,
                isLoadingVisible = isLoading
            )
        }
    }
}
