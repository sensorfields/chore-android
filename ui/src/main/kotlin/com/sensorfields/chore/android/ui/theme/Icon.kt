package com.sensorfields.chore.android.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun Icon(
    icon: Icons,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    androidx.compose.material3.Icon(
        imageVector = icon.imageVector,
        contentDescription = contentDescription,
        modifier = modifier,
    )
}
