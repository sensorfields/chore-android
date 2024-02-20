package com.sensorfields.chore.android.utils

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

public val LocalAppNavController: ProvidableCompositionLocal<NavController> =
    staticCompositionLocalOf {
        error("CompositionLocal AppNavController not present")
    }
