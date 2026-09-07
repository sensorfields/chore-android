package com.sensorfields.chore.android.ui.chore.details

import java.time.Instant

data class ChoreDetailsState(
    val name: String = "",
    val date: Instant? = null,
)
