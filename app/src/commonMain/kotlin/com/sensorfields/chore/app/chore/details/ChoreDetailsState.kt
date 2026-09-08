package com.sensorfields.chore.app.chore.details

import kotlin.time.Instant

public data class ChoreDetailsState(
    val name: String = "",
    val date: Instant? = null,
)
