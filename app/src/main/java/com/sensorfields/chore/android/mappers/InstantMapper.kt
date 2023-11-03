package com.sensorfields.chore.android.mappers

import io.realm.kotlin.types.RealmInstant
import java.time.Instant

fun Instant.toRealmInstant(): RealmInstant {
    return RealmInstant.from(
        epochSeconds = epochSecond,
        nanosecondAdjustment = nano
    )
}

fun RealmInstant.toInstant(): Instant {
    return Instant.ofEpochSecond(
        epochSeconds,
        nanosecondsOfSecond.toLong()
    )
}
