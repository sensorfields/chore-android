package com.sensorfields.chore.app.chore.create

import com.sensorfields.chore.domain.models.Chore

public sealed interface ChoreCreateNavigationAction {
    public data class Finish(val chore: Chore) : ChoreCreateNavigationAction
}
