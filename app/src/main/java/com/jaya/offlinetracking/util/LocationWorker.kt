package com.jaya.offlinetracking.util

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jaya.offlinetracking.LocationApp
import com.jaya.offlinetracking.repository.LocationRepository

class LocationWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val repository: LocationRepository by lazy {
        (applicationContext as LocationApp).repository
    }

    override suspend fun doWork(): Result {
        return try {
            // collect & save location OR sync
            repository.sync()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
