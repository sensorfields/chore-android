package com.sensorfields.chore.app.chore.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sensorfields.chore.domain.models.Chore
import com.sensorfields.chore.domain.usecases.ObserveChoreUseCase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metrox.viewmodel.ManualViewModelAssistedFactory
import dev.zacsweers.metrox.viewmodel.ManualViewModelAssistedFactoryKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@AssistedInject
public class ChoreDetailsViewModel(
    @Assisted private val choreId: String,
    private val observeChoreUseCase: ObserveChoreUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ChoreDetailsState.initial())
    public val state: StateFlow<ChoreDetailsState> = _state.asStateFlow()

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
            chore?.let { chore ->
                ChoreDetailsState.Chore(
                    name = chore.name,
                    date = chore.at.dateTime,
                )
            } ?: ChoreDetailsState.Empty
        }
    }

    @AssistedFactory
    @ManualViewModelAssistedFactoryKey
    @ContributesIntoMap(AppScope::class)
    public fun interface Factory : ManualViewModelAssistedFactory {
        public fun create(choreId: String): ChoreDetailsViewModel
    }
}
