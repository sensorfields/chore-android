package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun ChoreCreateWhereScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("WiFi")
        }
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Map")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ChoreCreateWhereScreen()
}
