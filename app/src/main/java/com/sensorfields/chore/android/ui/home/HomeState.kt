package com.sensorfields.chore.android.ui.home

data class HomeState(
    val currentScreen: Screen = Screen.DASHBOARD,
    val isCreateChoreButtonVisible: Boolean = true,
) {
    enum class Screen { DASHBOARD, HISTORY, SETTINGS }
}
