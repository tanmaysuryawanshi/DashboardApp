package com.tanmaysuryawanshi.dashboard.dashboardfeature.data.remote.dto

import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.model.Data

data class DataDto(
    val favourite_links: List<Any>,
    val overall_url_chart: Map<String,Int>,
    val recent_links: List<RecentLinkDto>,
    val top_links: List<TopLinkDto>
){
    fun toData(): Data {
        return Data(
            favourite_links = favourite_links,
            overall_url_chart = overall_url_chart,
            recent_links = recent_links.map { it.toRecentLink() },
            top_links = top_links.map { it.toTopLink() }
        )
    }
}