package com.sensorfields.chore.app.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sensorfields.chore.app.dashboard.DashboardAction.ShowChoreCreatedMessage
import com.sensorfields.chore.domain.models.Chore
import com.sensorfields.chore.domain.usecases.ObserveChoresUseCase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

@Inject
@ViewModelKey
@ContributesIntoMap(AppScope::class)
public class DashboardViewModel(
    private val observeChoresUseCase: ObserveChoresUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    public val state: StateFlow<DashboardState> = _state.asStateFlow()

    private val _actions = Channel<DashboardAction>(capacity = Channel.UNLIMITED)
    public val actions: Flow<DashboardAction> = _actions.receiveAsFlow()

    private var choreSort: MutableStateFlow<DashboardState.ChoreSort> =
        MutableStateFlow(state.value.choreSort)
    private var chores: List<Chore> = emptyList()

    init {
        observeChores()
    }

    public fun onChoreCreateResult(chore: Chore) {
        _actions.trySend(ShowChoreCreatedMessage(choreName = chore.name))
    }

    public fun onChoreSortByClick(sortBy: Chore.SortProperty) {
        choreSort.getAndUpdate { current ->
            if (current.sortBy == sortBy) {
                current.copy(isAscending = !current.isAscending)
            } else {
                DashboardState.ChoreSort(
                    sortBy = sortBy,
                    isAscending = when (sortBy) {
                        Chore.SortProperty.NAME -> true
                        Chore.SortProperty.DATE -> false
                    },
                )
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeChores() {
        choreSort
            .flatMapLatest {
                observeChoresUseCase(sortBy = it.sortBy, isAscending = it.isAscending)
            }
            .onEach {
                chores = it
                updateState()
            }
            .launchIn(viewModelScope)
    }

    private fun updateState() {
        _state.update {
            it.copy(
                choreSort = choreSort.value,
                choreItems = chores.toState(),
            )
        }
    }
}
