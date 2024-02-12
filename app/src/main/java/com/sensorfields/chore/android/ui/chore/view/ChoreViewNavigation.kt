package com.sensorfields.chore.android.ui.chore.view

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
import com.sensorfields.chore.android.ui.LocalAppNavController

fun NavGraphBuilder.choreView() {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(ID) { type = NavType.StringType }
        )
    ) {
        val appNavController = LocalAppNavController.current
        val viewModel = hiltViewModel<ChoreViewViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        ChoreViewScreen(
            state = state,
            onUpClick = appNavController::navigateUp
        )
    }
}

fun NavController.navigateToChoreView(choreId: String) {
    navigate(
        Uri.Builder()
            .path(PREFIX)
            .appendPath(choreId)
            .build()
            .toString()
    )
}

fun SavedStateHandle.getChoreViewId(): String = get(ID) ?: error("$ID not provided")

private const val PREFIX = "chore/view"
private const val ID = "id"
private const val ROUTE = "$PREFIX/{$ID}"
