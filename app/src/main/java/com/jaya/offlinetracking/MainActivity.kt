package com.jaya.offlinetracking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaya.offlinetracking.ui.theme.OfflineTrackingTheme
import com.jaya.offlinetracking.util.NetworkMonitor
import com.jaya.offlinetracking.viewmodel.MainViewModel
import com.jaya.offlinetracking.viewmodel.MainViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as LocationApp

        val viewModelFactory = MainViewModelFactory(
            repository = app.repository,
            networkMonitor = NetworkMonitor(this)
        )

        setContent {
            val viewModel: MainViewModel =  viewModel(factory = viewModelFactory)


            MainScreen(viewModel)
        }
    }
    }

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val context = LocalContext.current
    val pending by viewModel.pendingCount.collectAsState()
    val online by viewModel.isOnline.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Pending Logs: $pending")
        Text("Network: ${if (online) "Online" else "Offline"}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.startTracking(context) }) {
            Text("Start Tracking")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { viewModel.stopTracking(context) }) {
            Text("Stop Tracking")
        }
    }
}
