package com.sensorfields.chore.android.ui.chore.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.ui.theme.UpButton

@OptIn(ExperimentalMaterial3Api::class)
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
                navigationIcon = { UpButton(onClick = onUpClick) }
            )
        }
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
                textAlign = TextAlign.Center
            )
            state.date?.let {
                Text(
                    state.date,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFull() {
    ChoreDetailsScreen(
        state = ChoreDetailsState(
            name = "This is name",
            date = "2/13/88"
        ),
        onUpClick = {}
    )
}

@Preview
@Composable
private fun PreviewMin() {
    ChoreDetailsScreen(
        state = ChoreDetailsState(
            name = "This is name"
        ),
        onUpClick = {}
    )
}
