package com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.repository

import com.tanmaysuryawanshi.dashboard.core.util.Resource
import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.model.DashboardData
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    suspend fun getDashboardData(): Resource<DashboardData>
}