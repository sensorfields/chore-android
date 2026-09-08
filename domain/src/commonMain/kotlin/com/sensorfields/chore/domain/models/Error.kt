package com.sensorfields.chore.domain.models

public sealed interface Error {
    public data class General(val e: Exception) : Error
}
