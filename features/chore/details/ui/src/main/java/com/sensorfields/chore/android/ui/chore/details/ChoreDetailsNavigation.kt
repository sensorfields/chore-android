package com.sensorfields.chore.android.ui.chore.details

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.utils.LocalAppNavController

public fun NavGraphBuilder.choreDetails() {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(ID) { type = NavType.StringType }
        )
    ) {
        val appNavController = LocalAppNavController.current
        val viewModel = hiltViewModel<ChoreDetailsViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        ChoreDetailsScreen(
            state = state,
            onUpClick = appNavController::navigateUp
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

public fun SavedStateHandle.getChoreDetailsArgs(): Chore.Id {
    return get<String>(ID)?.let { Chore.Id(it) } ?: error("$ID not provided")
}

private const val PREFIX = "chore/details"
private const val ID = "id"
private const val ROUTE = "$PREFIX/{$ID}"
