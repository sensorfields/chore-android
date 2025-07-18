package com.sensorfields.chore.android.ui.chore.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.ui.chore.choreDate
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.BodyMediumText
import com.sensorfields.chore.android.ui.theme.Scaffold
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.android.ui.theme.TopAppBar
import com.sensorfields.chore.android.ui.theme.UpButton
import java.time.Instant

@Composable
internal fun ChoreDetailsScreen(
    state: ChoreDetailsState,
    onUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = { UpButton(onClick = onUpClick) },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                state.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
            state.date?.let {
                BodyMediumText(
                    choreDate(it),
                    modifier = Modifier.fillMaxWidth(),
                    variantColor = true,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFull() = AppTheme {
    ChoreDetailsScreen(
        state = ChoreDetailsState(
            name = "This is name",
            date = Instant.parse("1988-02-13T13:30:00Z"),
        ),
        onUpClick = {},
    )
}

@Preview
@Composable
private fun PreviewMin() = AppTheme {
    ChoreDetailsScreen(
        state = ChoreDetailsState(
            name = "This is name",
        ),
        onUpClick = {},
    )
}
