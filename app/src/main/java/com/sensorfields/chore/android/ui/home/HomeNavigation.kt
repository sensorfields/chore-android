package com.sensorfields.chore.android.ui.home

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sensorfields.chore.android.ui.chore.create.getChoreCreateResult
import com.sensorfields.chore.android.utils.FlowCollectEffect
import logcat.logcat

const val HOME_ROUTE = "home"

fun NavGraphBuilder.home() {
    composable(route = HOME_ROUTE) { backStackEntry ->
        val viewModel = hiltViewModel<HomeViewModel>()
        FlowCollectEffect(backStackEntry.getChoreCreateResult()) {
            logcat { "Chore Create Result: $it" }
        }
        val state by viewModel.state.collectAsStateWithLifecycle()
        HomeScreen(
            state = state,
            actions = viewModel.actions,
            onScreenChange = viewModel::onScreenChange
        )
    }
}
