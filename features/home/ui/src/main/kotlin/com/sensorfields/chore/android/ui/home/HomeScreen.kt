package com.sensorfields.chore.android.ui.home

import androidx.annotation.StringRes
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.sensorfields.chore.android.domain.models.Chore
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.serialization.Serializable

@Composable
internal fun HomeScreen(
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
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
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
        icon = { Icon(tabKey.iconVector, contentDescription = labelText) },
        label = { Text(labelText) },
    )
}

@Serializable
private enum class TabKey(
    val iconVector: ImageVector,
    @StringRes val labelId: Int,
) : NavKey {
    DASHBOARD(
        iconVector = Icons.Dashboard,
        labelId = R.string.home_navigation_dashboard,
    ),
    STATS(
        iconVector = Icons.QueryStats,
        labelId = R.string.home_navigation_stats,
    ),
    SETTINGS(
        iconVector = Icons.Settings,
        labelId = R.string.home_navigation_settings,
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
