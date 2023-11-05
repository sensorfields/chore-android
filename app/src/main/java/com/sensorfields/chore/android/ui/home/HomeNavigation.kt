package com.sensorfields.chore.android.ui.home

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sensorfields.chore.android.ui.chore.create.getChoreCreateResult
import com.sensorfields.chore.android.ui.chore.create.navigateToChoreCreate
import logcat.logcat

const val HOME_ROUTE = "home"

fun NavGraphBuilder.home(navController: NavController) {
    composable(route = HOME_ROUTE) { navBackStackEntry ->

        logcat { "AAAAAAAAAAAA: INIT HOME YOO: ${navBackStackEntry.savedStateHandle.getChoreCreateResult()}" }

        val viewModel = hiltViewModel<HomeViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        HomeScreen(
            state = state,
            onScreenChange = viewModel::onScreenChange,
            onCreateChoreClick = navController::navigateToChoreCreate
        )
    }
}
