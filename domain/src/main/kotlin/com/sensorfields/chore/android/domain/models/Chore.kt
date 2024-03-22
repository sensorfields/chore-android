package com.sensorfields.chore.android.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.Instant

@Parcelize
public data class Chore(
    val id: Id,
    val name: String,
    val date: Instant?,
) : Parcelable {

    @JvmInline
    @Parcelize
    public value class Id(public val value: String) : Parcelable

    public enum class SortProperty { NAME, DATE }
}
