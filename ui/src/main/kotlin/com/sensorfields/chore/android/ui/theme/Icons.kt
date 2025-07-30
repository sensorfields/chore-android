package com.sensorfields.chore.android.ui.theme

import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons as MaterialIcons

public sealed class Icons(internal val imageVector: ImageVector) {
    public data object ArrowUpward : Icons(MaterialIcons.Default.ArrowUpward)
    public data object ArrowDownward : Icons(MaterialIcons.Default.ArrowDownward)
    public data object Sort : Icons(MaterialIcons.AutoMirrored.Default.Sort)
    public data object Add : Icons(MaterialIcons.Default.Add)
    public data object Dashboard : Icons(MaterialIcons.Default.Dashboard)
    public data object QueryStats : Icons(MaterialIcons.Default.QueryStats)
    public data object Settings : Icons(MaterialIcons.Default.Settings)
    public data object Check : Icons(MaterialIcons.Default.Check)
}
