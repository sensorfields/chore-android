package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sensorfields.chore.android.domain.models.Chore
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.Finish
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.NavigateToWhen
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.NavigateToWhere
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.ShowError
import com.sensorfields.chore.android.utils.LoadingButton
import com.sensorfields.chore.android.utils.UpButton
import com.sensorfields.chore.android.utils.collectInEffect
import com.sensorfields.chore.android.utils.collectLatestInEffect
import com.sensorfields.chore.android.utils.getErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChoreCreateScreen(
    state: ChoreCreateState,
    actions: Flow<ChoreCreateAction>,
    onUpClick: () -> Unit,
    onScreenChange: (Screen) -> Unit,
    onNameChange: (String) -> Unit,
    onDateChange: (Long?) -> Unit,
    onNextClick: () -> Unit,
    onFinish: (Chore) -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    navController.currentBackStackEntryFlow.collectLatestInEffect {
        it.destination.route?.let { route -> onScreenChange(Screen.valueOf(route)) }
    }

    val latestOnFinish by rememberUpdatedState(onFinish)
    actions.collectInEffect { action ->
        when (action) {
            is Finish -> latestOnFinish(action.chore)
            NavigateToWhen -> navController.navigate(Screen.WHEN.name)
            NavigateToWhere -> navController.navigate(Screen.WHERE.name)
            is ShowError -> {
                snackbarHostState.showSnackbar(
                    message = context.getErrorMessage(action.error)
                )
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.chore_create_title)) },
                navigationIcon = { UpButton(onClick = onUpClick) }
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(BottomAppBarDefaults.windowInsets)
                    .imePadding()
                    .padding(16.dp)
            ) {
                LoadingButton(
                    onClick = onNextClick,
                    loading = state.isLoadingVisible,
                    enabled = state.isNextButtonEnabled
                ) {
                    Text(stringResource(R.string.chore_create_next_button))
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.WHAT.name,
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
        ) {
            composable(Screen.WHAT.name) {
                ChoreCreateWhatScreen(
                    name = state.name,
                    onNameChange = onNameChange,
                    onDoneClick = onNextClick
                )
            }
            composable(Screen.WHEN.name) {
                ChoreCreateWhenScreen(
                    date = state.date,
                    onDateChanged = onDateChange
                )
            }
            composable(Screen.WHERE.name) {
                ChoreCreateWhereScreen()
            }
        }
    }
}

internal enum class Screen {
    WHAT, WHEN, WHERE
}

@Preview
@Composable
private fun Preview() {
    ChoreCreateScreen(
        state = ChoreCreateState(),
        actions = emptyFlow(),
        onUpClick = {},
        onScreenChange = {},
        onNameChange = {},
        onDateChange = {},
        onNextClick = {},
        onFinish = {}
    )
}
