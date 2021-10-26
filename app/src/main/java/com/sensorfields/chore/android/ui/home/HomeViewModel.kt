package com.sensorfields.chore.android.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    private val _action = Channel<HomeAction>(capacity = Channel.UNLIMITED)
    val action: Flow<HomeAction> = _action.receiveAsFlow()

    fun trySend(event: HomeEvent) {
        logcat { "trySend: $event" }
    }
}
