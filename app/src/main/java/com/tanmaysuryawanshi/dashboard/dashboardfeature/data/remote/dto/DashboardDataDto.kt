package com.tanmaysuryawanshi.dashboard.dashboardfeature.data.remote.dto

import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.model.DashboardData

data class DashboardDataDto(
    val applied_campaign: Int,
    val data: DataDto?,
    val extra_income: Double,
    val links_created_today: Int,
    val message: String,
    val startTime: String,
    val status: Boolean,
    val statusCode: Int,
    val support_whatsapp_number: String,
    val today_clicks: Int,
    val top_location: String,
    val top_source: String,
    val total_clicks: Int,
    val total_links: Int
){
   fun toDashBoardData():DashboardData{
       return DashboardData(
           applied_campaign = applied_campaign,
           dataDto = data?.let { it.toData() },
           extra_income = extra_income,
           links_created_today = links_created_today,
           message = message,
           startTime = startTime,
           status = status,
           statusCode = statusCode,
           support_whatsapp_number = support_whatsapp_number,
           today_clicks = today_clicks,
           top_location = top_location,
           top_source = top_source,
           total_clicks = total_clicks,
           total_links = total_links
       )
   }
}