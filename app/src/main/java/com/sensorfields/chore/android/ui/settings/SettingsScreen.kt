package com.sensorfields.chore.android.ui.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.R

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
) {
    Text(stringResource(R.string.home_navigation_settings), modifier = modifier)
}

@Preview
@Composable
private fun Preview() {
    SettingsScreen()
}
