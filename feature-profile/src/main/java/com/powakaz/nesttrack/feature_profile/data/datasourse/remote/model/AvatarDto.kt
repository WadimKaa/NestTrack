package com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AvatarDto(
    @SerialName("avatar_url")
    val avatarUrl: String
) {
}