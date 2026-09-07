package com.sensorfields.chore.android.ui.home

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsetsSides.Companion.Bottom
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.sensorfields.chore.android.ui.dashboard.DashboardRoute
import com.sensorfields.chore.android.ui.settings.SettingsRoute
import com.sensorfields.chore.android.ui.stats.StatsRoute
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.Icon
import com.sensorfields.chore.android.ui.theme.Icons
import com.sensorfields.chore.android.ui.theme.NavigationBar
import com.sensorfields.chore.android.ui.theme.NavigationBarDefaults
import com.sensorfields.chore.android.ui.theme.NavigationBarItem
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.domain.models.Chore
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.home_navigation_dashboard
import com.sensorfields.chore.resources.home_navigation_settings
import com.sensorfields.chore.resources.home_navigation_stats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen(
    onNavigateToChoreCreate: () -> Unit,
    choreCreateResults: () -> Flow<Chore>,
    onNavigateToChoreDetails: (Chore.Id) -> Unit,
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(TabKey.DASHBOARD)

    Column(modifier = modifier.fillMaxSize()) {
        NavDisplay(
            backStack = backStack,
            modifier = Modifier
                .fillMaxWidth()
                .consumeWindowInsets(NavigationBarDefaults.windowInsets.only(Bottom))
                .weight(1f),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            predictivePopTransitionSpec = {
                ContentTransform(
                    targetContentEnter = EnterTransition.None,
                    initialContentExit = ExitTransition.None,
                    sizeTransform = null,
                )
            },
        ) { key ->
            if (key !is TabKey) error("Invalid key: $key")
            when (key) {
                TabKey.DASHBOARD -> NavEntry(key) {
                    DashboardRoute(
                        onNavigateToChoreCreate = onNavigateToChoreCreate,
                        choreCreateResults = choreCreateResults,
                        onNavigateToChoreDetails = onNavigateToChoreDetails,
                    )
                }

                TabKey.STATS -> NavEntry(key) {
                    StatsRoute()
                }

                TabKey.SETTINGS -> NavEntry(key) {
                    SettingsRoute()
                }
            }
        }
        NavigationBar(modifier = Modifier.fillMaxWidth()) {
            TabKey.entries.forEach { tabKey ->
                Item(
                    tabKey = tabKey,
                    selected = backStack.last() == tabKey,
                    onClick = {
                        when {
                            tabKey == TabKey.DASHBOARD && backStack.size > 1 -> {
                                backStack.removeAt(1)
                            }

                            backStack.size > 1 -> backStack[1] = tabKey
                            else -> backStack.add(tabKey)
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun RowScope.Item(
    tabKey: TabKey,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val labelText = stringResource(tabKey.labelId)
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = { Icon(tabKey.icon, contentDescription = labelText) },
        label = { Text(labelText) },
    )
}

@Serializable
private enum class TabKey(
    val icon: Icons,
    val labelId: StringResource,
) : NavKey {
    DASHBOARD(
        icon = Icons.Dashboard,
        labelId = Res.string.home_navigation_dashboard,
    ),
    STATS(
        icon = Icons.QueryStats,
        labelId = Res.string.home_navigation_stats,
    ),
    SETTINGS(
        icon = Icons.Settings,
        labelId = Res.string.home_navigation_settings,
    ),
}

@Preview
@Composable
private fun Preview() = AppTheme {
    HomeScreen(
        onNavigateToChoreCreate = {},
        choreCreateResults = { emptyFlow() },
        onNavigateToChoreDetails = {},
    )
}
