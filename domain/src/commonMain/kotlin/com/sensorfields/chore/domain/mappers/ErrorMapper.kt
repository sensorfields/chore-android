package com.sensorfields.chore.domain.mappers

import com.sensorfields.chore.domain.models.Error

internal fun Exception.toModel(): Error = Error.General(e = this)
