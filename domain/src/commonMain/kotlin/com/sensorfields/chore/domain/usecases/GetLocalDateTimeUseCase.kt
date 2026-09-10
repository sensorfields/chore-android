package com.sensorfields.chore.domain.usecases

import dev.zacsweers.metro.Inject
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

@Inject
public class GetLocalDateTimeUseCase(
    private val getInstantUseCase: GetInstantUseCase,
    private val timeZone: TimeZone,
) {
    public operator fun invoke(
        instant: Instant = getInstantUseCase(),
    ): LocalDateTime = instant.toLocalDateTime(timeZone)
}
