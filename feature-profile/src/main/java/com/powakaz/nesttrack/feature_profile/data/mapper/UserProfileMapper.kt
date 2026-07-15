package com.powakaz.nesttrack.feature_profile.data.mapper

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.vector.path
import com.powakaz.nesttrack.feature_profile.BuildConfig
import com.powakaz.nesttrack.feature_profile.data.datasourse.local.entites.UserProfileEntity
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.UpdateAvatarResponseDto
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.UserProfileResponseDto
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.UpdateProfileResponseDto
import com.powakaz.nesttrack.feature_profile.domain.model.Avatar
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateAvatar
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateProfile
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import java.time.LocalDate
import java.time.OffsetDateTime


/*fun UserProfileEntity.toDomain(): UserProfile {
    return UserProfile(
        id = id,
        name = name,
        birthDate = birthDate,
        avatarUrl = avatarUrl,
        createdAt = createdAt
    )
}*/



@RequiresApi(Build.VERSION_CODES.O)
fun UserProfileResponseDto.toDomain(): UserProfile {
    return UserProfile(
        id = id,
        name = name,
        birthDate = birthDate?.let { LocalDate.parse(it) },
        avatarUrl = if (avatarUrl == null) {
            Avatar.Default
        } else {
            Avatar.Remote(buildUrl(avatarUrl))
        },
        createdAt = OffsetDateTime.parse(createdAt).toLocalDate()
    )
}

fun UpdateProfileResponseDto.toDomain() : UpdateProfile {
    return UpdateProfile(
        status = status == "success"

    )
}

fun UpdateAvatarResponseDto.toDomain() : UpdateAvatar {
    return UpdateAvatar(
        status = status == "success",
        avatarUrl = avatarUrl
    )
}

fun UserProfile.toDto(): UserProfileResponseDto {
    return UserProfileResponseDto(
        id = id,
        name = name,
        birthDate = birthDate?.toString(),
        avatarUrl = when(avatarUrl) {
            is Avatar.Remote -> avatarUrl.url
            is Avatar.Local -> avatarUrl.path
            Avatar.Default -> null
        },
        createdAt = createdAt?.toString()
    )
}

private fun buildUrl(path: String): String {
    return when {
        path.startsWith("http") -> path
        else -> BuildConfig.BASE_URL_AVATAR + path
    }
}
