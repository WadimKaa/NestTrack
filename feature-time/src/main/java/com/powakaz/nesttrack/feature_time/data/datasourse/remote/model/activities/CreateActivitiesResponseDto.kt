package com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.activities

import kotlinx.serialization.Serializable


@Serializable
data class CreateActivitiesResponseDto(
    val id: Int,
    val status: String
)