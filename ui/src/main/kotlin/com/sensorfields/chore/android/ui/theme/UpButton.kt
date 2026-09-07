package com.sensorfields.chore.android.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.up_button
import org.jetbrains.compose.resources.stringResource

@Composable
public fun UpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        Icons.ArrowBack,
        contentDescription = stringResource(Res.string.up_button),
        onClick = onClick,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() = AppTheme {
    UpButton(onClick = {})
}
