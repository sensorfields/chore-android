package com.sensorfields.chore.android.domain.models

import java.time.Instant

public data class Chore(
    val id: Id,
    val name: String,
    val date: Instant?,
) {
    @JvmInline
    public value class Id(public val value: String)

    public enum class SortProperty { NAME, DATE }
}
