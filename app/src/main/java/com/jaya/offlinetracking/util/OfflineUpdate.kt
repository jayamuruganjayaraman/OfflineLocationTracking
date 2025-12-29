package com.jaya.offlinetracking.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.android.gms.location.LocationServices
import com.jaya.offlinetracking.LocationApp
import com.jaya.offlinetracking.model.LocationEntity
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class OfflineUpdate(context: Context,
                    params: WorkerParameters
) : CoroutineWorker(context, params) {
    private val repository =
        (applicationContext as LocationApp).repository

    override suspend fun doWork(): Result {

        val location = getLastKnownLocation(applicationContext)

        location?.let {
            repository.insertLocation(
                LocationEntity(
                    latitude = it.latitude,
                    longitude = it.longitude,
                    accuracy = it.accuracy,
                    timestamp = System.currentTimeMillis(),
                    speed = it.speed,
                    synced = false
                )
            )
        }

        return Result.success()
    }

    suspend fun getLastKnownLocation(context: Context): Location? =
        suspendCancellableCoroutine { cont ->

            val client =
                LocationServices.getFusedLocationProviderClient(context)

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@suspendCancellableCoroutine
            }
            client.lastLocation
                .addOnSuccessListener { location ->
                    cont.resume(location)
                }
                .addOnFailureListener {
                    cont.resume(null)
                }
        }

}