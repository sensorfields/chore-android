package com.sensorfields.chore.android.ui.chore.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.domain.usecases.ObserveChoreUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = ChoreDetailsViewModel.Factory::class)
internal class ChoreDetailsViewModel @AssistedInject constructor(
    private val observeChoreUseCase: ObserveChoreUseCase,
    @Assisted private val choreIdValue: String, // TODO value classes not supported?
) : ViewModel() {

    private val choreId = Chore.Id(choreIdValue)

    private val _state = MutableStateFlow(ChoreDetailsState())
    val state = _state.asStateFlow()

    private var chore: Chore? = null

    init {
        observeChore()
    }

    private fun observeChore() {
        observeChoreUseCase(choreId = choreId)
            .onEach {
                chore = it
                updateState()
            }
            .launchIn(viewModelScope)
    }

    private fun updateState() {
        _state.update {
            it.copy(
                name = chore?.name.orEmpty(),
                date = chore?.date
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(choreIdValue: String): ChoreDetailsViewModel
    }
}
