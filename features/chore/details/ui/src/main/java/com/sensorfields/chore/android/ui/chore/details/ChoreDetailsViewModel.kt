package com.sensorfields.chore.android.ui.chore.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.domain.usecases.ObserveChoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import logcat.asLog
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
internal class ChoreDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val observeChoreUseCase: ObserveChoreUseCase,
) : ViewModel() {

    private val choreId = savedStateHandle.getChoreDetailsArgs()

    private val _state = MutableStateFlow(ChoreDetailsState())
    val state = _state.asStateFlow()

    private var chore: Chore? = null

    init {
        observeChore()
    }

    private fun observeChore() = viewModelScope.launch {
        observeChoreUseCase(choreId = choreId).collectLatest { result ->
            result.onSuccess {
                chore = it
                updateState()
            }.onFailure { logcat { it.asLog() } }
        }
    }

    private fun updateState() {
        _state.update {
            it.copy(
                name = chore?.name.orEmpty(),
                date = chore?.date
            )
        }
    }
}