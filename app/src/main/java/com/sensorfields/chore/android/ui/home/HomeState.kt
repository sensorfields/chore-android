package com.sensorfields.chore.android.ui.home

data class HomeState(
    val currentScreen: Screen = Screen.DASHBOARD,
    val choreSort: ChoreSort = ChoreSort(sortBy = ChoreSortBy.NAME, isAscending = true),
    val isCreateChoreButtonVisible: Boolean = true,
) {
    enum class Screen { DASHBOARD, HISTORY, SETTINGS }

    enum class ChoreSortBy { NAME, DATE }

    data class ChoreSort(val sortBy: ChoreSortBy, val isAscending: Boolean)
}
