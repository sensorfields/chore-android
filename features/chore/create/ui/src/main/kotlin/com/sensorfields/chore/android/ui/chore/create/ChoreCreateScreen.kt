package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.ui.chore.create.ChoreCreateAction.ShowError
import com.sensorfields.chore.android.ui.collectInEffect
import com.sensorfields.chore.android.ui.getErrorMessage
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.BottomBar
import com.sensorfields.chore.android.ui.theme.CloseButton
import com.sensorfields.chore.android.ui.theme.LoadingButton
import com.sensorfields.chore.android.ui.theme.Scaffold
import com.sensorfields.chore.android.ui.theme.SnackbarHost
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.android.ui.theme.TopAppBar
import com.sensorfields.chore.android.ui.theme.rememberSnackbarHostState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.time.Instant

@Composable
internal fun ChoreCreateScreen(
    state: ChoreCreateState,
    actions: Flow<ChoreCreateAction>,
    onUpClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onRepeatClick: (ChoreCreateState.When.Repeat) -> Unit,
    onDateChange: (Instant?) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = rememberSnackbarHostState()
    val context = LocalContext.current

    actions.collectInEffect { action ->
        when (action) {
            is ShowError -> {
                snackbarHostState.showSnackbar(
                    message = context.getErrorMessage(action.error),
                )
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.chore_create_title)) },
                navigationIcon = { CloseButton(onClick = onUpClick) },
            )
        },
        bottomBar = {
            BottomBar {
                LoadingButton(
                    onClick = onNextClick,
                    loading = state.isLoadingVisible,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.isNextButtonEnabled,
                ) {
                    Text(stringResource(R.string.chore_create_next_button))
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
                .imePadding(),
        ) {
            when (state) {
                is ChoreCreateState.What -> ChoreCreateWhat(
                    name = state.name,
                    onNameChange = onNameChange,
                    onDoneClick = onNextClick,
                )

                ChoreCreateState.When -> ChoreCreateWhen(
                    onRepeatClick = onRepeatClick,
                )

                is ChoreCreateState.WhenDate -> ChoreCreateWhenDate(
                    date = state.date,
                    onDateChange = onDateChange,
                )

                ChoreCreateState.WhenTime -> ChoreCreateWhenTime(

                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() = AppTheme {
    ChoreCreateScreen(
        state = ChoreCreateState.What(),
        actions = emptyFlow(),
        onUpClick = {},
        onNameChange = {},
        onRepeatClick = {},
        onDateChange = {},
        onNextClick = {},
    )
}
