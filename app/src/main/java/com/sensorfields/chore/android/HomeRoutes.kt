package com.sensorfields.chore.android

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class HomeRoutes(
    val route: String,
    @StringRes val labelId: Int,
    val iconVector: ImageVector,
) {
    DASHBOARD(
        route = "dashboard",
        labelId = R.string.home_navigation_dashboard,
        iconVector = Icons.Default.Dashboard
    ),
    HISTORY(
        route = "history",
        labelId = R.string.home_navigation_history,
        iconVector = Icons.Default.History
    ),
    SETTINGS(
        route = "settings",
        labelId = R.string.home_navigation_settings,
        iconVector = Icons.Default.Settings
    )
}
