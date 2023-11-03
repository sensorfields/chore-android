package com.sensorfields.chore.android.ui.chore.create

sealed interface ChoreCreateAction {
    data object NavigateToWhen : ChoreCreateAction
    data object NavigateToWhere : ChoreCreateAction
    data object NavigateUp : ChoreCreateAction
    data class ShowError(val error: Exception) : ChoreCreateAction
}
