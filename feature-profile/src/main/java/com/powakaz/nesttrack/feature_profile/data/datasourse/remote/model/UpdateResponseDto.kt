package com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateResponseDto(
    val status: String
) {
}