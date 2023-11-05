package com.sensorfields.chore.android.ui.home

sealed interface HomeAction {
    data object ShowChoreCreatedMessage : HomeAction
}
