package com.sensorfields.chore.android.domain.models

import kotlinx.serialization.Serializable
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month

public data class Chore(
    val id: Id,
    val name: String,
    val at: When,
) {
    @JvmInline
    @Serializable
    public value class Id(public val value: String)

    public sealed interface When {

        public val time: LocalTime

        public data class Once(
            public override val time: LocalTime,
            public val date: LocalDate,
        ) : When

        public data class Daily(
            public override val time: LocalTime,
        ) : When

        public data class Weekly(
            public override val time: LocalTime,
            public val days: Set<DayOfWeek>,
        ) : When

        public data class Monthly(
            public override val time: LocalTime,
            public val days: Set<Int>,
        ) : When

        public data class Yearly(
            public override val time: LocalTime,
            public val days: Set<Pair<Month, Int>>,
        ) : When
    }

    public enum class SortProperty { NAME, DATE }
}
