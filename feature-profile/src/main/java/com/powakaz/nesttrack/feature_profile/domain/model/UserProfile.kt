package com.powakaz.nesttrack.feature_profile.domain.model

import java.time.LocalDate

data class UserProfile(
    val id: Int,
    val name: String,
    val birthDate: LocalDate?,
    val avatarUrl: Any?,
    val createdAt: LocalDate?,
   // val apiToken: String

)