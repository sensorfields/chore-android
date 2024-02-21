package com.sensorfields.chore.android.ui.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

public const val SETTINGS_ROUTE: String = "settings"

public fun NavGraphBuilder.settings() {
    composable(route = SETTINGS_ROUTE) {
        SettingsScreen()
    }
}
