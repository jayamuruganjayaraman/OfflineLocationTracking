package com.jaya.offlinetracking.model

data class LocationDto(
    val employeeId: String,
    val latitude: Double,
    val longitude: Double,
    val accuracy: Float,
    val timestamp: Long,
    val speed: Float?
)

fun LocationEntity.toDto() = LocationDto(
    employeeId = "EMP001",
    latitude = latitude,
    longitude = longitude,
    accuracy = accuracy,
    timestamp = timestamp,
    speed = speed
)

