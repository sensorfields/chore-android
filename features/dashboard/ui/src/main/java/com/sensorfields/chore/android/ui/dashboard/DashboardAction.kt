package com.sensorfields.chore.android.ui.dashboard

internal sealed interface DashboardAction {
    data object ShowChoreCreatedMessage : DashboardAction
}
