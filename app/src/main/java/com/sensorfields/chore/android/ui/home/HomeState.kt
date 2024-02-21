package com.sensorfields.chore.android.ui.home

data class HomeState(
    val currentScreen: Screen = Screen.DASHBOARD,
) {
    enum class Screen { DASHBOARD, STATS, SETTINGS }
}
