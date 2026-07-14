package com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponseDto(
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