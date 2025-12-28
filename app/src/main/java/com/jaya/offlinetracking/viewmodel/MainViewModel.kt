package com.jaya.offlinetracking.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaya.offlinetracking.repository.LocationRepository
import com.jaya.offlinetracking.util.LocationTrackingManager
import com.jaya.offlinetracking.util.NetworkMonitor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
private val repository: LocationRepository,
networkMonitor: NetworkMonitor
) : ViewModel() {

    val pendingCount = repository.pendingCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val isOnline = networkMonitor.isOnline
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun startTracking(context: Context) {
        LocationTrackingManager.start(context)
    }

    fun stopTracking(context: Context) {
        LocationTrackingManager.stop(context)
    }
}

