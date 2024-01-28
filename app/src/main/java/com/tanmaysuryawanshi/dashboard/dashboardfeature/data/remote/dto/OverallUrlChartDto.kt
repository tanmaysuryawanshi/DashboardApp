package com.tanmaysuryawanshi.dashboard.dashboardfeature.data.remote.dto

import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.model.OverallUrlChart

data class OverallUrlChartDto(
    val mapData : Map<String, Int>
){

    fun toOverallUrlChart(): OverallUrlChart {
        return OverallUrlChart(
            mapData = mapData
        )
    }
}