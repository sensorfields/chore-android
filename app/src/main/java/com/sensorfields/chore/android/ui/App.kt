package com.sensorfields.chore.android.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sensorfields.chore.android.Screen
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateScreen
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateViewModel
import com.sensorfields.chore.android.ui.home.HomeScreen

@Composable
fun App() {
    val navController = rememberNavController()
    MaterialTheme {
        NavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onCreateChoreClick = { navController.navigate(Screen.ChoreCreate.route) }
                )
            }
            composable(Screen.ChoreCreate.route) {
                val viewModel = hiltViewModel<ChoreCreateViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                ChoreCreateScreen(
                    state = state,
                    action = viewModel.action,
                    onUpClick = { navController.navigateUp() },
                    onNameChange = viewModel::onNameChange,
                    onDateChange = viewModel::onDateChange,
                    onNextClick = viewModel::onNextClick
                )
            }
        }
    }
}
