package com.sensorfields.chore.android.ui.home

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.sensorfields.chore.android.domain.models.Chore
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
public object HomeRoute

public fun NavGraphBuilder.home(
    onNavigateToChoreCreate: () -> Unit,
    choreCreateResults: () -> Flow<Chore>,
    onNavigateToChoreDetails: (Chore.Id) -> Unit,
) {
    composable<HomeRoute> {
        val viewModel = hiltViewModel<HomeViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        HomeScreen(
            state = state,
            onScreenChange = viewModel::onScreenChange,
            onNavigateToChoreCreate = onNavigateToChoreCreate,
            choreCreateResults = choreCreateResults,
            onNavigateToChoreDetails = onNavigateToChoreDetails
        )
    }
}

internal val NavController.homeNavOptions: NavOptions
    get() = navOptions {
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
