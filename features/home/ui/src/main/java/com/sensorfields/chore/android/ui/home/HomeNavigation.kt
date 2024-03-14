package com.sensorfields.chore.android.ui.home

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sensorfields.chore.android.domain.models.Chore
import kotlinx.coroutines.flow.Flow

public const val HOME_ROUTE: String = "home"

public fun NavGraphBuilder.home(
    onNavigateToChoreCreate: () -> Unit,
    choreCreateResults: () -> Flow<Boolean>?,
    onNavigateToChoreDetails: (Chore.Id) -> Unit,
) {
    composable(route = HOME_ROUTE) {
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
