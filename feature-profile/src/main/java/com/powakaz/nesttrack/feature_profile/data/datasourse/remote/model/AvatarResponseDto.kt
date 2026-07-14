package com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AvatarResponseDto(
    val status: String,
    @SerialName("avatar_url")
    val avatarUrl: String
) {
}