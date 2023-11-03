package com.sensorfields.chore.android.ui.chore.create

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChoreCreateViewModel @Inject constructor(
) : ViewModel() {

    private val _state = MutableStateFlow(ChoreCreateState())
    val state = _state.asStateFlow()

    private var name: String = ""

    fun onNameChange(name: String) {
        this.name = name
        updateState()
    }

    fun onNextClick() {

    }

    private fun updateState() {
        _state.update {
            it.copy(
                name = name,
                isNextButtonEnabled = name.isNotBlank()
            )
        }
    }
}
