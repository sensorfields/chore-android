package com.sensorfields.chore.android.ui.chore.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sensorfields.chore.android.domain.usecases.CreateChoreUseCase
import com.sensorfields.chore.android.ui.ActionChannel
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.ShowError
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateNavigationAction.Finish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
internal class ChoreCreateViewModel @Inject constructor(
    private val createChoreUseCase: CreateChoreUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ChoreCreateState())
    val state = _state.asStateFlow()

    private val _navigationAction = ActionChannel<ChoreCreateNavigationAction>()
    val navigationAction = _navigationAction.receiveAsFlow()

    private val _action = ActionChannel<ChoreCreateAction>()
    val action = _action.receiveAsFlow()

    private var screen: Screen = Screen.WHAT
    private var name: String = ""
    private var date: Instant? = null
    private var isLoading: Boolean = false

    fun onNameChange(name: String) {
        this.name = name
        updateState()
    }

    fun onDateChange(date: Instant?) {
        this.date = date
        updateState()
    }

    fun onNextClick() {
        when (screen) {
            Screen.WHAT -> {
                if (name.isNotEmpty()) {
                    screen = Screen.WHEN
                    updateState()
                }
            }

            Screen.WHEN -> {
                if (date != null) {
                    screen = Screen.WHERE
                    updateState()
                }
            }

            Screen.WHERE -> viewModelScope.launch {
                isLoading = true
                updateState()

                createChoreUseCase(name = name, date = date).onSuccess {
                    _navigationAction.trySend(Finish(it))
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
                isWhatExpanded = screen == Screen.WHAT,
                name = name,
                isWhenExpanded = screen == Screen.WHEN,
                date = date,
                isNextButtonEnabled = isNextButtonEnabled,
                isLoadingVisible = isLoading
            )
        }
    }
}
