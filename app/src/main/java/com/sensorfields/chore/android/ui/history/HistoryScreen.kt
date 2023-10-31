package com.sensorfields.chore.android.ui.history

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.R

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
) {
    Text(stringResource(R.string.home_navigation_history), modifier = modifier)
}

@Preview
@Composable
private fun Preview() {
    HistoryScreen()
}
