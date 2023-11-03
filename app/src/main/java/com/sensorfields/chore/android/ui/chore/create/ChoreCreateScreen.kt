package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sensorfields.chore.android.R
import com.sensorfields.chore.android.ui.UpButton
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.NavigateToWhen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ChoreCreateScreen(
    state: ChoreCreateState,
    action: Flow<ChoreCreateAction>,
    onUpClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onDateChange: (Long?) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        action.collect { action ->
            when (action) {
                NavigateToWhen -> navController.navigate(Screen.WHEN.name)
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
                Button(
                    onClick = onNextClick,
                    enabled = state.isNextButtonEnabled
                ) {
                    Text(stringResource(R.string.chore_create_next_button))
                }
            }
        }
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
        }
    }
}

private enum class Screen {
    WHAT, WHEN
}

@Preview
@Composable
private fun Preview() {
    ChoreCreateScreen(
        state = ChoreCreateState(),
        action = emptyFlow(),
        onUpClick = {},
        onNameChange = {},
        onDateChange = {},
        onNextClick = {}
    )
}
