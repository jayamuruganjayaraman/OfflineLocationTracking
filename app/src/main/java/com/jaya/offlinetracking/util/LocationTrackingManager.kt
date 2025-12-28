package com.jaya.offlinetracking.util

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object LocationTrackingManager {

    fun start(context: Context) {
        val workRequest =
            PeriodicWorkRequestBuilder<LocationWorker>(
                15, TimeUnit.MINUTES
            ).build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "location_tracking",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
    }

    fun stop(context: Context) {
        WorkManager.getInstance(context)
            .cancelUniqueWork("location_tracking")
    }
}
