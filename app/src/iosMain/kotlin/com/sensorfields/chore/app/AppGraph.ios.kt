package com.sensorfields.chore.app

import com.sensorfields.chore.app.chore.create.ChoreCreateViewModel
import com.sensorfields.chore.app.chore.details.ChoreDetailsViewModel
import com.sensorfields.chore.app.dashboard.DashboardViewModel
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.createGraph

@DependencyGraph(AppScope::class)
public interface IosAppGraph : AppGraph {
    public val choreCreateViewModel: ChoreCreateViewModel
    public val dashboardViewModel: DashboardViewModel
    public val choreDetailsViewModelFactory: ChoreDetailsViewModel.Factory
}

public fun createAppGraph(): IosAppGraph = createGraph()
