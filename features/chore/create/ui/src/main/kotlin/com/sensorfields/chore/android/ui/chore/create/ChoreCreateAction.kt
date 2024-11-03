package com.sensorfields.chore.android.ui.chore.create

internal sealed interface ChoreCreateAction {
    data class ShowError(val error: Throwable) : ChoreCreateAction
}
