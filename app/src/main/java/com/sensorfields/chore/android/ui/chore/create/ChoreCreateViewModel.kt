package com.sensorfields.chore.android.ui.chore.create

import androidx.lifecycle.ViewModel
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.NavigateToWhen
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

    private var name: String = ""
    private var date: Long? = null

    fun onNameChange(name: String) {
        this.name = name
        updateState()
    }

    fun onDateChange(date: Long?) {
        this.date = date
        updateState()
    }

    fun onNextClick() {
        if (name.isNotEmpty()) {
            _action.trySend(NavigateToWhen)
        }
    }

    private fun updateState() {
        _state.update {
            it.copy(
                name = name,
                date = date,
                isNextButtonEnabled = name.isNotBlank()
            )
        }
    }
}
