package com.sensorfields.chore.android.domain.mappers

import io.realm.kotlin.types.RealmInstant
import java.time.Instant

public fun Instant.toRealmInstant(): RealmInstant {
    return RealmInstant.from(
        epochSeconds = epochSecond,
        nanosecondAdjustment = nano
    )
}

public fun RealmInstant.toInstant(): Instant {
    return Instant.ofEpochSecond(
        epochSeconds,
        nanosecondsOfSecond.toLong()
    )
}
