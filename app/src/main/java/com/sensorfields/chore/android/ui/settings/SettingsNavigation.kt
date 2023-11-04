package com.sensorfields.chore.android.ui.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val SETTINGS_ROUTE = "settings"

fun NavGraphBuilder.settings() {
    composable(route = SETTINGS_ROUTE) {
        SettingsScreen()
    }
}
