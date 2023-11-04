package com.sensorfields.chore.android.ui.home

import androidx.lifecycle.ViewModel
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

    private val _action = Channel<HomeAction>(capacity = Channel.UNLIMITED)
    val action = _action.receiveAsFlow()

    private var currentScreen: HomeState.Screen = HomeState.Screen.DASHBOARD

    fun onScreenChange(screen: HomeState.Screen) {
        this.currentScreen = screen
        updateState()
    }

    private fun updateState() {
        _state.update {
            it.copy(
                currentScreen = currentScreen,
                isCreateChoreButtonVisible = currentScreen == HomeState.Screen.DASHBOARD
            )
        }
    }
}
