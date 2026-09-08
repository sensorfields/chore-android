package com.sensorfields.chore.android.ui.chore.details

import kotlin.time.Instant

data class ChoreDetailsState(
    val name: String = "",
    val date: Instant? = null,
)
