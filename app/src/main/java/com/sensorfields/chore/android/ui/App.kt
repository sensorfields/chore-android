package com.sensorfields.chore.android.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sensorfields.chore.android.ui.chore.create.choreCreate
import com.sensorfields.chore.android.ui.home.HOME_ROUTE
import com.sensorfields.chore.android.ui.home.home

@Composable
fun App() {
    val navController = rememberNavController()
    MaterialTheme {
        NavHost(navController = navController, startDestination = HOME_ROUTE) {
            home(navController)
            choreCreate(navController)
        }
    }
}
