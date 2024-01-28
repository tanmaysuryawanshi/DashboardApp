package com.tanmaysuryawanshi.dashboard.dashboardfeature.data.remote.api

import com.tanmaysuryawanshi.dashboard.dashboardfeature.data.remote.dto.DashboardDataDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface DashboardApi {

    @GET("api/v1/dashboardNew")
    @Headers("Content-Type: application/json")
   suspend fun getDashboardData(@Header("Authorization") authToken: String): Response<DashboardDataDto>

    companion object {
        const val BASE_URL = "https://api.inopenapp.com/"
        const val TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
    }


}