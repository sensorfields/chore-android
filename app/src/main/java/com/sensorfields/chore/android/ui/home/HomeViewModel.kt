package com.sensorfields.chore.android.ui.home

import androidx.lifecycle.ViewModel
import com.sensorfields.chore.android.ui.home.HomeAction.ShowChoreCreatedMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _actions = Channel<HomeAction>(capacity = Channel.UNLIMITED)
    val actions = _actions.receiveAsFlow()

    private var currentScreen: HomeState.Screen = state.value.currentScreen

    fun onScreenChange(screen: HomeState.Screen) {
        this.currentScreen = screen
        updateState()
    }

    fun onChoreCreateResult(result: Boolean) {
        _actions.trySend(ShowChoreCreatedMessage)
    }

    private fun updateState() {
        _state.update { it.copy(currentScreen = currentScreen) }
    }
}
