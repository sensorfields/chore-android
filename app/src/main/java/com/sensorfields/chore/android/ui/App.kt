package com.sensorfields.chore.android.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sensorfields.chore.android.ui.chore.create.choreCreate
import com.sensorfields.chore.android.ui.chore.view.choreView
import com.sensorfields.chore.android.ui.home.HOME_ROUTE
import com.sensorfields.chore.android.ui.home.home

@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
    ) {
        val navController = rememberNavController()
        CompositionLocalProvider(LocalAppNavController provides navController) {
            NavHost(navController = navController, startDestination = HOME_ROUTE) {
                home()
                choreCreate()
                choreView()
            }
        }
    }
}

val LocalAppNavController = staticCompositionLocalOf<NavController> {
    error("CompositionLocal AppNavController not present")
}
