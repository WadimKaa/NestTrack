package com.powakaz.nesttrack.feature_time.data.datasourse.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ActivitiesResponseDto(
    val id: Int,
    val name: String,
    @SerialName("icon_name")
    val iconName: String,
    @SerialName("color_hex")
    val iconColor: String
) {
}