package com.sensorfields.chore.android.domain.models

import com.sensorfields.chore.android.utils.InstantSerializable
import kotlinx.serialization.Serializable

@Serializable
public data class Chore(
    val id: Id,
    val name: String,
    val date: InstantSerializable?,
    // val repeat: Repeat,
) {
    @JvmInline
    @Serializable
    public value class Id(public val value: String)

//    @Serializable
//    public sealed interface Repeat {
//
//        public val interval: Int
//        public val time: LocalTime
//
//        @Serializable
//        public data class Daily(
//            public override val interval: Int,
//            public override val time: LocalTime,
//        ) : Repeat
//
//        @Serializable
//        public data class Weekly(
//            public override val interval: Int,
//            public override val time: LocalTime,
//            public val days: Set<DayOfWeek>,
//        ) : Repeat
//
//        @Serializable
//        public data class Monthly(
//            public override val interval: Int,
//            public override val time: LocalTime,
//            public val days: Set<Int>,
//        ) : Repeat
//
//        @Serializable
//        public data class Yearly(
//            public override val interval: Int,
//            public override val time: LocalTime,
//            public val days: Set<Pair<Month, Int>>,
//        ) : Repeat
//    }

    public enum class SortProperty { NAME, DATE }
}
