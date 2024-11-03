package com.sensorfields.chore.android.domain.models

import com.sensorfields.chore.android.utils.InstantSerializable
import kotlinx.serialization.Serializable

@Serializable
public data class Chore(
    val id: Id,
    val name: String,
    val date: InstantSerializable?,
) {

    @JvmInline
    @Serializable
    public value class Id(public val value: String)

    public enum class SortProperty { NAME, DATE }
}
