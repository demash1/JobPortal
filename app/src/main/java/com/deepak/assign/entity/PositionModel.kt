package com.deepak.assign.entity

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

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
    /***
     * Convert date to dd/MM/yyyy format
     * @return String
     */
    fun convertDate(): String {
        val date: Date? = if (createdAt.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})".toRegex())) {
            SimpleDateFormat("yyyy-MM-dd").parse(createdAt)
        } else
            SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy").parse(createdAt)
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(date)
    }
}


