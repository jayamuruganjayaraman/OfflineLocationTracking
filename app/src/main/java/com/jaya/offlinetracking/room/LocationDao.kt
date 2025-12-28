package com.jaya.offlinetracking.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jaya.offlinetracking.model.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(location: LocationEntity)

    @Query("SELECT * FROM location_logs WHERE synced = 0 ORDER BY timestamp ASC")
    suspend fun getUnsynced(): List<LocationEntity>

    @Query("UPDATE location_logs SET synced = 1 WHERE id IN (:ids)")
    suspend fun markSynced(ids: List<Long>)

    @Query("SELECT COUNT(*) FROM location_logs WHERE synced = 0")
    fun observePendingCount(): Flow<Int>
}