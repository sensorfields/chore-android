package com.sensorfields.chore.android.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public data class ChoreEntity(
    @PrimaryKey val id: String,
    val name: String,
    val date: String?,
)
