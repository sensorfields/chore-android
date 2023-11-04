package com.sensorfields.chore.android.ui.history

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HISTORY_ROUTE = "history"

fun NavGraphBuilder.history() {
    composable(route = HISTORY_ROUTE) {
        HistoryScreen()
    }
}
