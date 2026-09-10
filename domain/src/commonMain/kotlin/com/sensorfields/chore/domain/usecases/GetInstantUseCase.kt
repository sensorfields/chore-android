package com.sensorfields.chore.domain.usecases

import dev.zacsweers.metro.Inject
import kotlin.time.Clock
import kotlin.time.Instant

@Inject
public class GetInstantUseCase(
    private val clock: Clock,
) {
    public operator fun invoke(): Instant = clock.now()
}
