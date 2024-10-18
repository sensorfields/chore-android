package com.sensorfields.chore.android.ui.chore.details

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sensorfields.chore.android.domain.models.Chore
import kotlinx.serialization.Serializable

public fun NavGraphBuilder.choreDetails(navController: NavController) {
    composable<ChoreDetailsRoute> { entry ->
        val viewModel = hiltViewModel<ChoreDetailsViewModel, ChoreDetailsViewModel.Factory> {
            it.create(entry.toRoute<ChoreDetailsRoute>().choreId)
        }
        val state by viewModel.state.collectAsStateWithLifecycle()
        ChoreDetailsScreen(
            state = state,
            onUpClick = navController::navigateUp
        )
    }
}

public fun NavController.navigateToChoreDetails(choreId: Chore.Id) {
    navigate(ChoreDetailsRoute(choreId = choreId.value))
}

@Serializable
private data class ChoreDetailsRoute(val choreId: String)
