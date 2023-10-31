package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChoreCreateScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Text(
            stringResource(R.string.home_chore_create_button),
            modifier = Modifier
                .fillMaxWidth()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ChoreCreateScreen()
}
