package com.sensorfields.chore.android.ui.chore.create

internal sealed interface ChoreCreateAction {
    data object NavigateToWhen : ChoreCreateAction
    data object NavigateToWhere : ChoreCreateAction
    data object Finish : ChoreCreateAction
    data class ShowError(val error: Throwable) : ChoreCreateAction
}
