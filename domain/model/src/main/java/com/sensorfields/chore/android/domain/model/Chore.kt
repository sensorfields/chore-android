package com.sensorfields.chore.android.domain.model

import java.time.Instant

public data class Chore(
    val id: String,
    val name: String,
    val date: Instant?,
) {
    public enum class SortProperty { NAME, DATE }
}
