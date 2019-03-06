package com.deepak.assign.entity

data class SearchModel(
    val end_date: String,
    val id: String,
    val locations: List<String>,
    val maximum: Int,
    val minimum: Int,
    val organization_name: String,
    val position_title: String,
    val rate_interval_code: String,
    val start_date: String,
    val url: String
)