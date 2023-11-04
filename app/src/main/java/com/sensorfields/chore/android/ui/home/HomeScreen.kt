package com.sensorfields.chore.android.ui.home

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sensorfields.chore.android.R
import com.sensorfields.chore.android.ui.dashboard.DASHBOARD_ROUTE
import com.sensorfields.chore.android.ui.dashboard.dashboard
import com.sensorfields.chore.android.ui.history.HISTORY_ROUTE
import com.sensorfields.chore.android.ui.history.history
import com.sensorfields.chore.android.ui.settings.SETTINGS_ROUTE
import com.sensorfields.chore.android.ui.settings.settings
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onScreenChange: (HomeState.Screen) -> Unit,
    onCreateChoreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        navController.currentBackStackEntryFlow.collectLatest {
            when {
                it.isSelected(DASHBOARD_ROUTE) -> onScreenChange(HomeState.Screen.DASHBOARD)
                it.isSelected(HISTORY_ROUTE) -> onScreenChange(HomeState.Screen.HISTORY)
                it.isSelected(SETTINGS_ROUTE) -> onScreenChange(HomeState.Screen.SETTINGS)
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                HomeState.Screen.entries.forEach { navigationItem ->
                    val route: String
                    val labelText: String
                    val iconVector: ImageVector
                    when (navigationItem) {
                        HomeState.Screen.DASHBOARD -> {
                            route = DASHBOARD_ROUTE
                            labelText = stringResource(R.string.home_navigation_dashboard)
                            iconVector = Icons.Default.Dashboard
                        }

                        HomeState.Screen.HISTORY -> {
                            route = HISTORY_ROUTE
                            labelText = stringResource(R.string.home_navigation_history)
                            iconVector = Icons.Default.History
                        }

                        HomeState.Screen.SETTINGS -> {
                            route = SETTINGS_ROUTE
                            labelText = stringResource(R.string.home_navigation_settings)
                            iconVector = Icons.Default.Settings
                        }
                    }

                    NavigationBarItem(
                        selected = state.currentScreen == navigationItem,
                        onClick = {
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(iconVector, contentDescription = labelText) },
                        label = { Text(labelText) }
                    )
                }
            }
        },
        floatingActionButton = {
            if (state.isCreateChoreButtonVisible) {
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
            startDestination = DASHBOARD_ROUTE,
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
        ) {
            dashboard(onNavigateToChoreUpdate = {})
            history()
            settings()
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
        state = HomeState(),
        onScreenChange = {},
        onCreateChoreClick = {}
    )
}
