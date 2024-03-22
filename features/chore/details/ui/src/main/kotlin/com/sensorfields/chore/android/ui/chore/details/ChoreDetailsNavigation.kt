package com.sensorfields.chore.android.ui.chore.details

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sensorfields.chore.android.domain.models.Chore

public fun NavGraphBuilder.choreDetails(navController: NavController) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(CHORE_ID) { type = NavType.StringType }
        )
    ) { entry ->
        val viewModel = hiltViewModel<ChoreDetailsViewModel, ChoreDetailsViewModel.Factory> {
            it.create(entry.getChoreId())
        }
        val state by viewModel.state.collectAsStateWithLifecycle()
        ChoreDetailsScreen(
            state = state,
            onUpClick = navController::navigateUp
        )
    }
}

public fun NavController.navigateToChoreDetails(choreId: Chore.Id) {
    navigate(
        Uri.Builder()
            .path(PREFIX)
            .appendPath(choreId.value)
            .build()
            .toString()
    )
}

private fun NavBackStackEntry.getChoreId(): String {
    return arguments?.getString(CHORE_ID) ?: error("$CHORE_ID not provided")
}

private const val PREFIX = "chore/details"
private const val CHORE_ID = "choreId"
private const val ROUTE = "$PREFIX/{$CHORE_ID}"
