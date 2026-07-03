package com.powakaz.nesttrack.feature_profile.domain.model

data class UserProfile(
    val id: Int,
    val name: String,
    val birthDate: Long?,
    val avatarUrl: String?,
    val createdAt: Long?,
    val apiToken: String?

)