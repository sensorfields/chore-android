package com.sensorfields.chore.android.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sensorfields.chore.android.HomeRoutes

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val navHierarchy = navBackStackEntry?.destination?.hierarchy

    Column(modifier = modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = HomeRoutes.DASHBOARD.route,
            modifier = modifier.weight(1f)
        ) {
            composable(HomeRoutes.DASHBOARD.route) {
                Text("DASHBOARD YO YOO")
            }
            composable(HomeRoutes.HISTORY.route) {
                Text("HISTORY YO YOO")
            }
            composable(HomeRoutes.SETTINGS.route) {
                Text("SETTINGS YO YOO")
            }
        }
        NavigationBar {
            HomeRoutes.entries.forEach { route ->
                NavigationBarItem(
                    selected = navHierarchy?.any { it.route == route.route } == true,
                    onClick = {
                        navController.navigate(route.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(route.iconVector, contentDescription = null) },
                    label = { Text(stringResource(route.labelId)) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeScreen()
}
