package com.sensorfields.chore.domain.models

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

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
