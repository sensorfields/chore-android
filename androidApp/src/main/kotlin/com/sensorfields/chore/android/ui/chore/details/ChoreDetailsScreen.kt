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
import androidx.compose.ui.tooling.preview.PreviewWrapper
import com.sensorfields.chore.android.ui.chore.choreDate
import com.sensorfields.chore.theme.AppPreviewWrapper
import com.sensorfields.chore.theme.BodyMediumText
import com.sensorfields.chore.theme.Scaffold
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.TopAppBar
import com.sensorfields.chore.theme.UpButton
import kotlin.time.Instant

@Composable
fun ChoreDetailsScreen(
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
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun PreviewFull() {
    ChoreDetailsScreen(
        state = ChoreDetailsState(
            name = "This is name",
            date = Instant.parse("1988-02-13T13:30:00Z"),
        ),
        onUpClick = {},
    )
}

@Preview
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun PreviewMin() {
    ChoreDetailsScreen(
        state = ChoreDetailsState(
            name = "This is name",
        ),
        onUpClick = {},
    )
}
