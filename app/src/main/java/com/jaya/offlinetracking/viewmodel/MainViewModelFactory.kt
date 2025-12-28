package com.jaya.offlinetracking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jaya.offlinetracking.repository.LocationRepository
import com.jaya.offlinetracking.util.NetworkMonitor

class MainViewModelFactory(
    private val repository: LocationRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository, networkMonitor) as T
    }
}
