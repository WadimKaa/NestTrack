package com.powakaz.nesttrack.feature_profile.domain.model



sealed interface Avatar {

    data object Default : Avatar

    data class Remote(
        val url: String
    ) : Avatar

    data class Local(
        val path: String
    ) : Avatar
}