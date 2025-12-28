package com.jaya.offlinetracking.util

import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.jaya.offlinetracking.model.LocationEntity
import com.jaya.offlinetracking.repository.LocationRepository

class LocationTracker(private val repository: LocationRepository
) : LocationCallback() {

    override fun onLocationResult(result: LocationResult) {
        result.locations.forEach { location ->
            repository.insertLocation(
                LocationEntity(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    accuracy = location.accuracy,
                    timestamp = System.currentTimeMillis(),
                    speed = location.speed
                )
            )
        }
    }
}