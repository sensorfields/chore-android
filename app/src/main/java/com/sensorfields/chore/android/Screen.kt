package com.sensorfields.chore.android

sealed class Screen(open val route: String) {
    data object Home : Screen("home")
    data object ChoreCreate : Screen("chore/create")
}
