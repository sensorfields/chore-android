package com.sensorfields.chore.android.ui.chore.create

import java.time.Instant

internal data class ChoreCreateState(
    val isWhatExpanded: Boolean = true,
    val name: String = "",
    val isWhenExpanded: Boolean = false,
    val date: Instant? = null,
    val isNextButtonEnabled: Boolean = false,
    val isLoadingVisible: Boolean = false,
)
