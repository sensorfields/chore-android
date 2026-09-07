package com.sensorfields.chore.domain.mappers

import kotlin.time.Instant

internal fun String.toInstant(): Instant = Instant.parse(this)
