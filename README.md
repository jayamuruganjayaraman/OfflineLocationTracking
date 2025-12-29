# OfflineLocationTracking
This Android application continuously tracks device location, stores it locally when offline, and synchronizes the data with a remote server when network connectivity is available.
The app is built using MVVM architecture, Jetpack Compose, Room, and WorkManager to ensure reliability and scalability.

Architecture Used : MVVM (Model–View–ViewModel)
UI Layer : Jetpack Compose
Design Pattern : Repository Pattern, Observable
Offline Storage : Room Database
Network Connectivity Monitoring : ConnectivityManager, Callback flow
Background Processing: WorkManager
API Implementation : Data class with dto and Retrofit