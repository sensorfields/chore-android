package com.sensorfields.chore.android.ui.chore.create

import com.sensorfields.chore.android.domain.models.Chore

internal sealed interface ChoreCreateAction {
    data class Finish(val chore: Chore) : ChoreCreateAction
    data object NavigateToWhen : ChoreCreateAction
    data object NavigateToWhere : ChoreCreateAction
    data class ShowError(val error: Throwable) : ChoreCreateAction
}
