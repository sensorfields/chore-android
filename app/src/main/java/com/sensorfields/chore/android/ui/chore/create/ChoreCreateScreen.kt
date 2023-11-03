package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sensorfields.chore.android.R
import com.sensorfields.chore.android.ui.LoadingButton
import com.sensorfields.chore.android.ui.UpButton
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.NavigateToWhen
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.NavigateToWhere
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.NavigateUp
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.ShowError
import com.sensorfields.chore.android.ui.getErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import logcat.asLog
import logcat.logcat

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ChoreCreateScreen(
    state: ChoreCreateState,
    action: Flow<ChoreCreateAction>,
    onUpClick: () -> Unit,
    onScreenChange: (Screen) -> Unit,
    onNameChange: (String) -> Unit,
    onDateChange: (Long?) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        launch {
            navController.currentBackStackEntryFlow.collectLatest {
                try {
                    it.destination.route?.let { route -> onScreenChange(Screen.valueOf(route)) }
                } catch (e: Exception) {
                    logcat { e.asLog() }
                }
            }
        }
        launch {
            action.collect { action ->
                when (action) {
                    NavigateToWhen -> navController.navigate(Screen.WHEN.name)
                    NavigateToWhere -> navController.navigate(Screen.WHERE.name)
                    NavigateUp -> onUpClick()
                    is ShowError -> {
                        snackbarHostState.showSnackbar(
                            message = context.getErrorMessage(action.error)
                        )
                    }
                }
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

enum class Screen {
    WHAT, WHEN, WHERE
}

@Preview
@Composable
private fun Preview() {
    ChoreCreateScreen(
        state = ChoreCreateState(),
        action = emptyFlow(),
        onUpClick = {},
        onScreenChange = {},
        onNameChange = {},
        onDateChange = {},
        onNextClick = {}
    )
}
