package com.tanmaysuryawanshi.dashboard.dashboardfeature.data.remote.dto

import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.model.TopLink

data class TopLinkDto(
    val app: String,
    val created_at: String,
    val domain_id: String,
    val is_favourite: Boolean,
    val original_image: String,
    val smart_link: String,
    val thumbnail: String?,
    val times_ago: String,
    val title: String,
    val total_clicks: Int,
    val url_id: Int,
    val url_prefix: String?,
    val url_suffix: String,
    val web_link: String
){
    fun toTopLink(): TopLink {
        return TopLink(
            app = app,
            created_at = created_at,
            domain_id = domain_id,
            is_favourite = is_favourite,
            original_image = original_image,
            smart_link = smart_link,
            thumbnail = thumbnail,
            times_ago = times_ago,
            title = title,
            total_clicks = total_clicks,
            url_id = url_id,
            url_prefix = url_prefix,
            url_suffix = url_suffix,
            web_link = web_link
        )
    }}