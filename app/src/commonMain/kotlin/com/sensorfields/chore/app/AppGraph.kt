package com.sensorfields.chore.app

import androidx.lifecycle.ViewModel
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import dev.zacsweers.metrox.viewmodel.ManualViewModelAssistedFactory
import dev.zacsweers.metrox.viewmodel.MetroViewModelFactory
import dev.zacsweers.metrox.viewmodel.ViewModelAssistedFactory
import dev.zacsweers.metrox.viewmodel.ViewModelGraph
import kotlinx.datetime.TimeZone
import kotlin.reflect.KClass
import kotlin.time.Clock

public interface AppGraph : ViewModelGraph {

    @Provides
    @SingleIn(AppScope::class)
    public fun timeZone(): TimeZone = TimeZone.currentSystemDefault()

    @Provides
    @SingleIn(AppScope::class)
    public fun clock(): Clock = Clock.System
}

@Inject
@ContributesBinding(AppScope::class)
@SingleIn(AppScope::class)
public class ViewModelFactory(
    override val viewModelProviders: Map<KClass<out ViewModel>, () -> ViewModel>,
    override val assistedFactoryProviders: Map<KClass<out ViewModel>, () -> ViewModelAssistedFactory>,
    override val manualAssistedFactoryProviders: Map<KClass<out ManualViewModelAssistedFactory>, () -> ManualViewModelAssistedFactory>,
) : MetroViewModelFactory()
