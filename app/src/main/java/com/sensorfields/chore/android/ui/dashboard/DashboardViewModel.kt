package com.sensorfields.chore.android.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.domain.usecases.ObserveChoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val observeChoresUseCase: ObserveChoresUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    private var choreSort: DashboardState.ChoreSort = state.value.choreSort
    private var chores: List<Chore> = emptyList()

    init {
        observeChores()
    }

    fun onChoreSortByClick(sortBy: DashboardState.ChoreSortBy) {
        choreSort = if (choreSort.sortBy == sortBy) {
            choreSort.copy(isAscending = !choreSort.isAscending)
        } else {
            DashboardState.ChoreSort(
                sortBy = sortBy,
                isAscending = when (sortBy) {
                    DashboardState.ChoreSortBy.NAME -> true
                    DashboardState.ChoreSortBy.DATE -> false
                }
            )
        }
        updateState()
    }

    private fun observeChores() = viewModelScope.launch {
        observeChoresUseCase().collectLatest {
            chores = it
            updateState()
        }
    }

    private fun updateState() {
        _state.update {
            it.copy(
                choreSort = choreSort,
                chores = chores.toState()
            )
        }
    }
}
