package com.sensorfields.chore.android.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
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
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.ui.dashboard.DASHBOARD_ROUTE
import com.sensorfields.chore.android.ui.dashboard.dashboard
import com.sensorfields.chore.android.ui.settings.SETTINGS_ROUTE
import com.sensorfields.chore.android.ui.settings.settings
import com.sensorfields.chore.android.ui.stats.STATS_ROUTE
import com.sensorfields.chore.android.ui.stats.stats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun HomeScreen(
    state: HomeState,
    onScreenChange: (HomeState.Screen) -> Unit,
    onNavigateToChoreCreate: () -> Unit,
    choreCreateResults: () -> Flow<Chore>,
    onNavigateToChoreDetails: (Chore.Id) -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        navController.currentBackStackEntryFlow.collectLatest {
            when {
                it.isSelected(DASHBOARD_ROUTE) -> onScreenChange(HomeState.Screen.DASHBOARD)
                it.isSelected(STATS_ROUTE) -> onScreenChange(HomeState.Screen.STATS)
                it.isSelected(SETTINGS_ROUTE) -> onScreenChange(HomeState.Screen.SETTINGS)
            }
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = DASHBOARD_ROUTE,
            modifier = Modifier
                .fillMaxWidth()
                .consumeWindowInsets(NavigationBarDefaults.windowInsets.only(WindowInsetsSides.Bottom))
                .weight(1f)
        ) {
            dashboard(
                onNavigateToChoreCreate = onNavigateToChoreCreate,
                choreCreateResults = choreCreateResults,
                onNavigateToChoreDetails = onNavigateToChoreDetails
            )
            stats()
            settings()
        }
        NavigationBar(modifier = Modifier.fillMaxWidth()) {
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

                    HomeState.Screen.STATS -> {
                        route = STATS_ROUTE
                        labelText = stringResource(R.string.home_navigation_stats)
                        iconVector = Icons.Default.QueryStats
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
        onNavigateToChoreCreate = {},
        choreCreateResults = { emptyFlow() },
        onNavigateToChoreDetails = {}
    )
}
