package com.deepak.assign.entity

data class CombinedModel(
    val company: String?,
    val companyLogo: String?,
    val companyUrl: String?,
    val createdAt: String?,
    val description: String?,
    val howToApply: String?,
    val pid: String?,
    val location: String?,
    val title: String?,
    val type: String?,
    val purl: String?,
    val end_date: String?,
    val id: String?,
    val locations: List<String?>,
    val maximum: Int,
    val minimum: Int,
    val organization_name: String?,
    val position_title: String?,
    val rate_interval_code: String?,
    val start_date: String?,
    val url: String?
)