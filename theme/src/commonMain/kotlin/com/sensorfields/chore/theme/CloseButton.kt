package com.sensorfields.chore.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.close_button
import org.jetbrains.compose.resources.stringResource

@Composable
public fun CloseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        Icons.Close,
        contentDescription = stringResource(Res.string.close_button),
        onClick = onClick,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() = AppTheme {
    CloseButton(onClick = {})
}
