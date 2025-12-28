package com.jaya.offlinetracking.repository

import com.jaya.offlinetracking.model.LocationEntity
import com.jaya.offlinetracking.model.toDto
import com.jaya.offlinetracking.room.LocationDao
import kotlinx.coroutines.flow.Flow

class LocationRepository(
    private val dao: LocationDao,
    private val api: LocationApi
) {

    fun insertLocation(location: LocationEntity) {
        dao.insert(location)
    }

    suspend fun sync() {
        val unsynced = dao.getUnsynced()
        if (unsynced.isEmpty()) return

        unsynced.forEach { location ->
            api.uploadLocation(location.toDto())
            dao.markSynced(listOf(location.id))
        }
    }

    fun pendingCount(): Flow<Int> =
        dao.observePendingCount()
}
