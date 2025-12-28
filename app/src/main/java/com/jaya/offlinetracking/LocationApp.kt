package com.jaya.offlinetracking

import android.app.Application
import androidx.room.Room
import com.jaya.offlinetracking.repository.LocationRepository
import com.jaya.offlinetracking.room.AppDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationApp : Application() {

    lateinit var database: AppDatabase
    lateinit var repository: LocationRepository
    lateinit var api: LocationApi

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "location_db"
        ).build()

        api = Retrofit.Builder()
            .baseUrl("https://mockapi.example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocationApi::class.java)

        repository = LocationRepository(
            dao = database.locationDao(),
            api = api
        )
    }
}
