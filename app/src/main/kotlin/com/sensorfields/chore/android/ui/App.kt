package com.sensorfields.chore.android.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.sensorfields.chore.android.ui.Route.Home
import com.sensorfields.chore.android.ui.home.HomeRoute
import com.sensorfields.chore.android.ui.theme.AppTheme
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.serialization.Serializable

@Composable
fun App() {
    AppTheme {
        val backStack = rememberNavBackStack<Route>(Home)
        NavDisplay(
            backStack = backStack,
            entryDecorators = listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = { key ->
                when (key as Route) {
                    Home -> NavEntry(key) {
                        HomeRoute(
                            onNavigateToChoreCreate = {},
                            choreCreateResults = { emptyFlow() },
                            onNavigateToChoreDetails = {},
                        )
                    }
                }
            }
        )

//        NavHost(navController = navController, startDestination = HomeRoute) {
//            home(
//                onNavigateToChoreCreate = navController::navigateToChoreCreate,
//                choreCreateResults = { navController.choreCreateResults },
//                onNavigateToChoreDetails = navController::navigateToChoreDetails
//            )
//            choreCreate(navController)
//            choreDetails(navController)
//        }
    }
}

sealed interface Route : NavKey {

    @Serializable
    data object Home : Route
}
