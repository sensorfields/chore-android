package com.sensorfields.chore.android.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.Scaffold
import com.sensorfields.chore.android.ui.theme.Text

@Composable
internal fun SettingsScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
        ) {
            Text(
                stringResource(R.string.settings_title),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() = AppTheme {
    SettingsScreen()
}
