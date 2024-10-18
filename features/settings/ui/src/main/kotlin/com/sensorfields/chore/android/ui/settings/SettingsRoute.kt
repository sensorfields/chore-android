package com.sensorfields.chore.android.ui.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
public object SettingsRoute

public fun NavGraphBuilder.settings() {
    composable<SettingsRoute> {
        SettingsScreen()
    }
}

public fun NavController.navigateToSettings(navOptions: NavOptions) {
    navigate(SettingsRoute, navOptions)
}
