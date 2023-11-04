package com.sensorfields.chore.android.ui.home

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sensorfields.chore.android.HomeRoutes
import com.sensorfields.chore.android.R
import com.sensorfields.chore.android.ui.dashboard.DashboardScreen
import com.sensorfields.chore.android.ui.dashboard.DashboardViewModel
import com.sensorfields.chore.android.ui.history.HistoryScreen
import com.sensorfields.chore.android.ui.settings.SettingsScreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    onCreateChoreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                HomeRoutes.entries.forEach { route ->
                    val labelText = stringResource(route.labelId)
                    NavigationBarItem(
                        selected = navBackStackEntry.isSelected(route.route),
                        onClick = {
                            navController.navigate(route.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(route.iconVector, contentDescription = labelText) },
                        label = { Text(labelText) }
                    )
                }
            }
        },
        floatingActionButton = {
            if (navBackStackEntry.isSelected(HomeRoutes.DASHBOARD.route)) {
                FloatingActionButton(onClick = onCreateChoreClick) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = stringResource(R.string.home_chore_create_button)
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeRoutes.DASHBOARD.route,
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
        ) {
            composable(HomeRoutes.DASHBOARD.route) {
                val viewModel = hiltViewModel<DashboardViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                DashboardScreen(
                    state = state,
                    onChoreClick = {
                        // TODO navigate
                    }
                )
            }
            composable(HomeRoutes.HISTORY.route) { HistoryScreen() }
            composable(HomeRoutes.SETTINGS.route) { SettingsScreen() }
        }
    }
}

private fun NavBackStackEntry?.isSelected(route: String): Boolean {
    return this?.destination?.hierarchy?.any { it.route == route } == true
}

@Preview
@Composable
private fun Preview() {
    HomeScreen(
        onCreateChoreClick = {}
    )
}
