package com.tanmaysuryawanshi.dashboard.dashboardfeature.data.repository_impl

import android.util.Log
import com.tanmaysuryawanshi.dashboard.core.util.Resource
import com.tanmaysuryawanshi.dashboard.dashboardfeature.data.remote.api.DashboardApi
import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.model.DashboardData
import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

class DashboardRepositoryImpl (private  val api:DashboardApi):DashboardRepository {
    override suspend fun getDashboardData(): Resource<DashboardData> {
        return  try {
            val response = api.getDashboardData("Bearer ${DashboardApi.TOKEN}")

            if (response.isSuccessful) {
                Log.d("success repo", response.body()!!.toString())
                Resource.Success(response.body()!!.toDashBoardData())
            } else { Log.d("else repo", "getDashboardData: ")
                Resource.Error("Error response: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d("error repo", e.message.toString())
            Resource.Error("Network error: ${e.message}")
        }

    }
}