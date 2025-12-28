package com.jaya.offlinetracking.util

import android.content.Context
import android.location.LocationRequest
import android.os.Looper
import com.bumptech.glide.Priority
import com.google.android.gms.location.LocationServices

class LocationProvider(private val context: Context) {
    private val fusedClient =
        LocationServices.getFusedLocationProviderClient(context)

    fun getLocationRequest(interval: Long): LocationRequest =
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, interval)
            .setMinUpdateIntervalMillis(interval)
            .build()

    fun requestLocationUpdates(
        interval: Long,
        callback: LocationCallback
    ) {
        fusedClient.requestLocationUpdates(
            getLocationRequest(interval),
            callback,
            Looper.getMainLooper()
        )
    }

    fun stop(callback: LocationCallback) {
        fusedClient.removeLocationUpdates(callback)
    }
}