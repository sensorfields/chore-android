package com.sensorfields.chore.android.ui.chore.create

import com.sensorfields.chore.android.domain.models.Chore

internal sealed interface ChoreCreateNavigationAction {
    data class Finish(val chore: Chore) : ChoreCreateNavigationAction
}
