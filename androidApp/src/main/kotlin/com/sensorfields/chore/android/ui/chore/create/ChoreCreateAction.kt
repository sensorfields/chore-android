package com.sensorfields.chore.android.ui.chore.create

import com.sensorfields.chore.domain.models.Error

sealed interface ChoreCreateAction {
    data class ShowError(val error: Error) : ChoreCreateAction
}
