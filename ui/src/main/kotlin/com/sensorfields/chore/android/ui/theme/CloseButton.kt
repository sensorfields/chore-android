package com.sensorfields.chore.android.ui.theme

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
    IconButton(
        Icons.Close,
        contentDescription = stringResource(R.string.close_button),
        onClick = onClick,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() = AppTheme {
    CloseButton(onClick = {})
}
