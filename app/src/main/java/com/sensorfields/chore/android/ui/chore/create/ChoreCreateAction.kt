package com.sensorfields.chore.android.ui.chore.create

sealed interface ChoreCreateAction {
    data object NavigateToWhen : ChoreCreateAction
}
