package com.powakaz.nesttrack.feature_time.domain.model.avatar

sealed interface Avatar {

    data class Default(
        val id: Int?
    ): Avatar

    data class Remote(
        val url: String
    ) : Avatar
}