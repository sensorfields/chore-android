package com.sensorfields.chore.android.ui.dashboard

sealed interface DashboardAction {
    data class ShowChoreCreatedMessage(val choreName: String) : DashboardAction
}
