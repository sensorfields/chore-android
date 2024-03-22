package com.sensorfields.chore.android.ui.dashboard

internal sealed interface DashboardAction {
    data class ShowChoreCreatedMessage(val choreName: String) : DashboardAction
}
