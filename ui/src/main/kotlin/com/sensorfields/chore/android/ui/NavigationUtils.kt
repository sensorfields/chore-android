package com.sensorfields.chore.android.ui

import androidx.lifecycle.asFlow
import androidx.navigation.NavBackStackEntry
import com.sensorfields.chore.android.domain.models.Chore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public inline fun <reified T> NavBackStackEntry?.setResult(key: String, value: T) {
    this?.savedStateHandle?.set(key, Json.encodeToString(value))
}

public inline fun <reified T> NavBackStackEntry?.observeResult(key: String): Flow<T> {
    return this?.savedStateHandle?.getLiveData<String>(key)
        ?.asFlow()
        ?.onEach { savedStateHandle.remove<Chore>(key) }
        ?.map { Json.decodeFromString<T>(it) }
        ?: emptyFlow()
}
