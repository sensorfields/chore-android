package com.sensorfields.chore.android

sealed class Screen(open val route: String) {

    object Home : Screen("home")
}
