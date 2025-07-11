package com.sensorfields.chore.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.ui.Route.ChoreCreate
import com.sensorfields.chore.android.ui.Route.ChoreDetails
import com.sensorfields.chore.android.ui.Route.Home
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateRoute
import com.sensorfields.chore.android.ui.chore.details.ChoreDetailsRoute
import com.sensorfields.chore.android.ui.home.HomeRoute
import com.sensorfields.chore.android.ui.theme.AppTheme
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.Serializable

@Composable
fun App() {
    AppTheme {
        val backStack = rememberNavBackStack<Route>(Home)

        val choreCreateResults = remember { ActionChannel<Chore>() }

        NavDisplay(
            backStack = backStack,
            entryDecorators = listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = { key ->
                if (key !is Route) error("Invalid key: $key")
                when (key) {
                    Home -> NavEntry(key) {
                        HomeRoute(
                            onNavigateToChoreCreate = { backStack.add(ChoreCreate) },
                            choreCreateResults = { choreCreateResults.receiveAsFlow() },
                            onNavigateToChoreDetails = { choreId ->
                                backStack.add(ChoreDetails(choreId = choreId))
                            },
                        )
                    }

                    ChoreCreate -> NavEntry(key) {
                        ChoreCreateRoute(
                            onNavigateUp = backStack::removeLastOrNull,
                            onFinish = { chore ->
                                choreCreateResults.trySend(chore)
                                backStack.removeLastOrNull()
                            },
                        )
                    }

                    is ChoreDetails -> NavEntry(key) {
                        ChoreDetailsRoute(
                            choreId = key.choreId,
                            onNavigateUp = backStack::removeLastOrNull,
                        )
                    }
                }
            }
        )
    }
}

sealed interface Route : NavKey {
    @Serializable
    data object Home : Route

    @Serializable
    data object ChoreCreate : Route

    @Serializable
    data class ChoreDetails(val choreId: Chore.Id) : Route
}
