package com.sensorfields.chore.android.ui.home

import androidx.compose.runtime.Composable
import com.sensorfields.chore.domain.models.Chore
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeRoute(
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
