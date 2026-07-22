package com.powakaz.nesttrack.feature_time.data.datasourse.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConcessionListResponseDto(
    val page: Int,
    @SerialName("total_records")
    val totalRecords: Int? = null,
    @SerialName("has_more")
    val hasMorePage: Boolean,
    @SerialName("data")
    val dataConcession: List<ConcessionDto>
) {
}

@Serializable
data class ConcessionDto(
    val id: Int,
    @SerialName("giver_name")
    val giverName: String,
    @SerialName("receiver_name")
    val receiverName: String,
    @SerialName("activity_name")
    val activityName: String,
    @SerialName("activity_icon")
    val activityIcon: String,
    @SerialName("activity_color")
    val activityIconColor: String,
    @SerialName("duration_hours")
    val durationHours: Double,
    val description: String?,
    @SerialName("created_at")
    val createdAt: String

) {
}