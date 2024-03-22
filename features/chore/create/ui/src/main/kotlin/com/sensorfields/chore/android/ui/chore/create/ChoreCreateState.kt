package com.sensorfields.chore.android.ui.chore.create

internal data class ChoreCreateState(
    val name: String = "",
    val date: Long? = null,
    val isNextButtonEnabled: Boolean = false,
    val isLoadingVisible: Boolean = false,
)
