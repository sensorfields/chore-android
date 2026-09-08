package com.sensorfields.chore.app.chore.create

import com.sensorfields.chore.domain.models.Chore
import com.sensorfields.chore.domain.models.Error

public sealed interface ChoreCreateAction {
    public data class ShowError(val error: Error) : ChoreCreateAction
    public data class Finish(val chore: Chore) : ChoreCreateAction
}
