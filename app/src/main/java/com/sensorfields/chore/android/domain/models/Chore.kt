package com.sensorfields.chore.android.domain.models

import java.time.Instant

data class Chore(
    val id: String,
    val name: String,
    val date: Instant?,
)
