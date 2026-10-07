package com.sensorfields.chore.app.chore.details

import kotlinx.datetime.LocalDateTime

public sealed interface ChoreDetailsState {
    public data object Empty : ChoreDetailsState
    public data class Chore(val name: String, val date: LocalDateTime) : ChoreDetailsState

    public companion object {
        public fun initial(): ChoreDetailsState = Empty
    }
}
