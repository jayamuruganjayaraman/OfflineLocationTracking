package com.jaya.offlinetracking.util

import com.jaya.offlinetracking.model.LocationDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LocationApi {
    @POST("locations")
    suspend fun uploadLocation(
        @Body location: LocationDto
    ): Response<Unit>
}