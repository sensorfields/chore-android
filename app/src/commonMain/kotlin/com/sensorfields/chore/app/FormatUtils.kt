package com.sensorfields.chore.app

import com.sensorfields.chore.domain.models.Error
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.error_general
import org.jetbrains.compose.resources.getString

public suspend fun Error.getMessage(): String = when (this) {
    is Error.General -> getString(Res.string.error_general)
}
