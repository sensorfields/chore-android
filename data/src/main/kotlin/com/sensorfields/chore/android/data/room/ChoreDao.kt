package com.sensorfields.chore.android.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sensorfields.chore.android.data.room.entities.ChoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
public interface ChoreDao {

    @Query("SELECT * FROM ChoreEntity ORDER BY :orderBy")
    public fun find(orderBy: String): Flow<List<ChoreEntity>>

    @Query("SELECT * FROM ChoreEntity WHERE id = :id")
    public fun observe(id: String): Flow<ChoreEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public suspend fun insert(chore: ChoreEntity)
}
