package com.sensorfields.chore.android.domain.mappers

import io.realm.kotlin.types.RealmInstant
import java.time.Instant

internal fun Instant.toRealmInstant(): RealmInstant {
    return RealmInstant.from(
        epochSeconds = epochSecond,
        nanosecondAdjustment = nano
    )
}

internal fun RealmInstant.toInstant(): Instant {
    return Instant.ofEpochSecond(
        epochSeconds,
        nanosecondsOfSecond.toLong()
    )
}
