package com.sensorfields.chore.domain.models

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import kotlinx.datetime.atDate

public data class Chore(
    val id: String,
    val name: String,
    val at: When,
) {
    public sealed interface When {

        public val time: LocalTime

        public val dateTime: LocalDateTime
            get() = when (this) {
                is Once -> time.atDate(date)
                is Daily,
                is Weekly,
                is Monthly,
                is Yearly,
                    -> time.atDate(1, 1, 1)
            }

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
