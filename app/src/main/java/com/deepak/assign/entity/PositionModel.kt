package com.deepak.assign.entity

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

data class PositionModel(
    val company: String,
    @SerializedName("company_logo")
    val companyLogo: String?,
    @SerializedName("company_url")
    val companyUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    val description: String?,
    @SerializedName("how_to_apply")
    val howToApply: String?,
    val id: String,
    val location: String,
    val title: String,
    val type: String?,
    val url: String
) {
    fun convertDate(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(createdAt)
    }
}


