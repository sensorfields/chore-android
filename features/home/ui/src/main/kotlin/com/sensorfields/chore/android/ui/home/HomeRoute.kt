package com.sensorfields.chore.android.ui.home

import androidx.compose.runtime.Composable
import com.sensorfields.chore.android.domain.models.Chore
import kotlinx.coroutines.flow.Flow

@Composable
public fun HomeRoute(
    onNavigateToChoreCreate: () -> Unit,
    choreCreateResults: () -> Flow<Chore>,
    onNavigateToChoreDetails: (Chore.Id) -> Unit,
) {
    HomeScreen(
        onNavigateToChoreCreate = onNavigateToChoreCreate,
        choreCreateResults = choreCreateResults,
        onNavigateToChoreDetails = onNavigateToChoreDetails,
    )
}
