package com.powakaz.nesttrack.feature_profile.data.mapper

import com.powakaz.nesttrack.feature_profile.data.datasourse.local.entites.UserProfileEntity
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile


fun UserProfileEntity.toDomain(): UserProfile {
    return UserProfile(
        id = id,
        name = name,
        birthDate = birthDate,
        avatarUrl = avatarUrl,
        createdAt = createdAt
    )
}