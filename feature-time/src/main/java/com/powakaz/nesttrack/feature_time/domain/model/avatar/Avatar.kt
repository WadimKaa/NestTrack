package com.powakaz.nesttrack.feature_time.domain.model.avatar

sealed interface Avatar {

    data object Default: Avatar

    data class Remote(
        val url: String
    ) : Avatar
}