package com.sensorfields.chore.android.ui.dashboard

sealed interface DashboardAction {
    data object ShowChoreCreatedMessage : DashboardAction
}
