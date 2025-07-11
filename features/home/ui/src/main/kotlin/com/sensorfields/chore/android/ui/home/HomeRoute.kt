package com.sensorfields.chore.android.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.sensorfields.chore.android.domain.models.Chore
import kotlinx.coroutines.flow.Flow

@Composable
public fun HomeRoute(
    onNavigateToChoreCreate: () -> Unit,
    choreCreateResults: () -> Flow<Chore>,
    onNavigateToChoreDetails: (Chore.Id) -> Unit,
) {
    val viewModel = viewModel<HomeViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onScreenChange = viewModel::onScreenChange,
        onNavigateToChoreCreate = onNavigateToChoreCreate,
        choreCreateResults = choreCreateResults,
        onNavigateToChoreDetails = onNavigateToChoreDetails
    )
}

@Deprecated("Replace with Nav3")
internal val NavController.homeNavOptions: NavOptions
    get() = navOptions {
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
