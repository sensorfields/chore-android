package com.sensorfields.chore.app.dashboard

public sealed interface DashboardAction {
    public data class ShowChoreCreatedMessage(val choreName: String) : DashboardAction
}
