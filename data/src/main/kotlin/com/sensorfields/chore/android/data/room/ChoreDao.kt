package com.sensorfields.chore.android.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.sensorfields.chore.android.data.room.entities.ChoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
public abstract class ChoreDao {

    public fun find(orderBy: String, isAscending: Boolean): Flow<List<ChoreEntity>> {
        val orderDirection = if (isAscending) "ASC" else "DESC"
        return find(
            SimpleSQLiteQuery("SELECT * FROM ChoreEntity ORDER BY $orderBy $orderDirection"),
        )
    }

    @Query("SELECT * FROM ChoreEntity WHERE id = :id")
    public abstract fun observe(id: String): Flow<ChoreEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract suspend fun insert(chore: ChoreEntity)

    @RawQuery(observedEntities = [ChoreEntity::class])
    internal abstract fun find(query: SupportSQLiteQuery): Flow<List<ChoreEntity>>
}
