package com.sensorfields.chore.android.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.ui.dashboard.DashboardRoute
import com.sensorfields.chore.android.ui.dashboard.dashboard
import com.sensorfields.chore.android.ui.dashboard.navigateToDashboard
import com.sensorfields.chore.android.ui.settings.SettingsRoute
import com.sensorfields.chore.android.ui.settings.navigateToSettings
import com.sensorfields.chore.android.ui.settings.settings
import com.sensorfields.chore.android.ui.stats.StatsRoute
import com.sensorfields.chore.android.ui.stats.navigateToStats
import com.sensorfields.chore.android.ui.stats.stats
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.Icon
import com.sensorfields.chore.android.ui.theme.Icons
import com.sensorfields.chore.android.ui.theme.NavigationBar
import com.sensorfields.chore.android.ui.theme.NavigationBarDefaults
import com.sensorfields.chore.android.ui.theme.NavigationBarItem
import com.sensorfields.chore.android.ui.theme.Text
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
                it.isSelected<DashboardRoute>() -> onScreenChange(HomeState.Screen.DASHBOARD)
                it.isSelected<StatsRoute>() -> onScreenChange(HomeState.Screen.STATS)
                it.isSelected<SettingsRoute>() -> onScreenChange(HomeState.Screen.SETTINGS)
            }
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = DashboardRoute,
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
            HomeState.Screen.entries.forEach { screen ->
                Item(
                    screen = screen,
                    selected = state.currentScreen == screen,
                    onClick = {
                        when (screen) {
                            HomeState.Screen.DASHBOARD -> navController
                                .navigateToDashboard(navController.homeNavOptions)

                            HomeState.Screen.STATS -> navController
                                .navigateToStats(navController.homeNavOptions)

                            HomeState.Screen.SETTINGS -> navController
                                .navigateToSettings(navController.homeNavOptions)
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun RowScope.Item(
    screen: HomeState.Screen,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val labelText: String
    val iconVector: ImageVector
    when (screen) {
        HomeState.Screen.DASHBOARD -> {
            labelText = stringResource(R.string.home_navigation_dashboard)
            iconVector = Icons.Dashboard
        }

        HomeState.Screen.STATS -> {
            labelText = stringResource(R.string.home_navigation_stats)
            iconVector = Icons.QueryStats
        }

        HomeState.Screen.SETTINGS -> {
            labelText = stringResource(R.string.home_navigation_settings)
            iconVector = Icons.Settings
        }
    }
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = { Icon(iconVector, contentDescription = labelText) },
        label = { Text(labelText) }
    )
}

private inline fun <reified T : Any> NavBackStackEntry?.isSelected(): Boolean {
    return this?.destination?.hierarchy?.any { it.hasRoute<T>() } == true
}

@Preview
@Composable
private fun Preview() = AppTheme {
    HomeScreen(
        state = HomeState(),
        onScreenChange = {},
        onNavigateToChoreCreate = {},
        choreCreateResults = { emptyFlow() },
        onNavigateToChoreDetails = {}
    )
}
