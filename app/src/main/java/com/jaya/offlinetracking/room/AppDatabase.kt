package com.jaya.offlinetracking.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jaya.offlinetracking.model.LocationEntity

@Database(entities = [LocationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}