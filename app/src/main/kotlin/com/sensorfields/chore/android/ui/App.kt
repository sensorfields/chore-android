package com.sensorfields.chore.android.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sensorfields.chore.android.ui.chore.create.choreCreate
import com.sensorfields.chore.android.ui.chore.create.choreCreateResults
import com.sensorfields.chore.android.ui.chore.create.navigateToChoreCreate
import com.sensorfields.chore.android.ui.chore.details.choreDetails
import com.sensorfields.chore.android.ui.chore.details.navigateToChoreDetails
import com.sensorfields.chore.android.ui.home.HomeRoute
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
        NavHost(navController = navController, startDestination = HomeRoute) {
            home(
                onNavigateToChoreCreate = navController::navigateToChoreCreate,
                choreCreateResults = { navController.choreCreateResults },
                onNavigateToChoreDetails = navController::navigateToChoreDetails
            )
            choreCreate(navController)
            choreDetails(navController)
        }
    }
}
