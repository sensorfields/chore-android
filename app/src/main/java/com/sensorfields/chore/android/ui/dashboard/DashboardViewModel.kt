package com.sensorfields.chore.android.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sensorfields.chore.android.domain.model.Chore
import com.sensorfields.chore.android.domain.usecase.ObserveChoresUseCase
import com.sensorfields.chore.android.ui.dashboard.DashboardAction.ShowChoreCreatedMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val observeChoresUseCase: ObserveChoresUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    private val _actions = Channel<DashboardAction>(capacity = Channel.UNLIMITED)
    val actions = _actions.receiveAsFlow()

    private var choreSort: MutableStateFlow<DashboardState.ChoreSort> =
        MutableStateFlow(state.value.choreSort)
    private var chores: List<Chore> = emptyList()

    init {
        observeChores()
    }

    fun onChoreCreateResult(result: Boolean) {
        _actions.trySend(ShowChoreCreatedMessage)
    }

    fun onChoreSortByClick(sortBy: Chore.SortProperty) {
        choreSort.getAndUpdate { current ->
            if (current.sortBy == sortBy) {
                current.copy(isAscending = !current.isAscending)
            } else {
                DashboardState.ChoreSort(
                    sortBy = sortBy,
                    isAscending = when (sortBy) {
                        Chore.SortProperty.NAME -> true
                        Chore.SortProperty.DATE -> false
                    }
                )
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeChores() = viewModelScope.launch {
        choreSort
            .flatMapLatest {
                observeChoresUseCase(sortBy = it.sortBy, isAscending = it.isAscending)
            }
            .collectLatest {
                chores = it
                updateState()
            }
    }

    private fun updateState() {
        _state.update {
            it.copy(
                choreSort = choreSort.value,
                choreItems = chores.toState()
            )
        }
    }
}
