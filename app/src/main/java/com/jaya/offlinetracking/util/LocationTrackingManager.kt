package com.jaya.offlinetracking.util

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object LocationTrackingManager {

    fun start(context: Context) {

        val locationWork =
            PeriodicWorkRequestBuilder<LocationWorker>(
                15, TimeUnit.MINUTES
            ).build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "location_worker",
                ExistingPeriodicWorkPolicy.KEEP,
                locationWork
            )


        val syncWork =
            PeriodicWorkRequestBuilder<OfflineUpdate>(
                15, TimeUnit.MINUTES
            )
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "sync_worker",
                ExistingPeriodicWorkPolicy.KEEP,
                syncWork
            )

    }

    fun stop(context: Context) {
        WorkManager.getInstance(context).cancelUniqueWork("location_worker")
        WorkManager.getInstance(context).cancelUniqueWork("sync_worker")
    }
}
