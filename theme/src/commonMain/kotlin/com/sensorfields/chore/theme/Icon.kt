package com.sensorfields.chore.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sensorfields.chore.resources.Icons
import com.sensorfields.chore.resources.imageVector

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
