package com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.avatar

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponseDto(
    val id: Int,
    val name: String,
    @SerialName("avatar_url")
    val avatarUrl: String?,
    @SerialName("birth_date")
    val birthDate: String?,
    @SerialName("created_at")
    val createdAt: String?,
)
{
}