package com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileResponseDto(
    val status: String
) {
}