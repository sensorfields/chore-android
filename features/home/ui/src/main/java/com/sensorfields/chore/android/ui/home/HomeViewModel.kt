package com.sensorfields.chore.android.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private var currentScreen: HomeState.Screen = state.value.currentScreen

    fun onScreenChange(screen: HomeState.Screen) {
        this.currentScreen = screen
        updateState()
    }

    private fun updateState() {
        _state.update { it.copy(currentScreen = currentScreen) }
    }
}
