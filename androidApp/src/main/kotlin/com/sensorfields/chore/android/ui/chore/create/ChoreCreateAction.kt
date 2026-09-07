package com.sensorfields.chore.android.ui.chore.create

sealed interface ChoreCreateAction {
    data class ShowError(val error: Throwable) : ChoreCreateAction
}
