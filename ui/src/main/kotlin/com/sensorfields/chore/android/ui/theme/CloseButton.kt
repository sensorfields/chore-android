package com.sensorfields.chore.android.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.ui.R

@Composable
public fun CloseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            Icons.Default.Close,
            contentDescription = stringResource(R.string.close_button),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = AppTheme {
    CloseButton(onClick = {})
}
