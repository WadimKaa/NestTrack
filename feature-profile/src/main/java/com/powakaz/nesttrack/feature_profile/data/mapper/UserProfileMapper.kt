package com.powakaz.nesttrack.feature_profile.data.mapper

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.powakaz.nesttrack.feature_profile.BuildConfig
import com.powakaz.nesttrack.feature_profile.data.datasourse.local.entites.UserProfileEntity
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.ProfileResponseDto
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.UpdateResponseDto
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateProfile
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import java.time.LocalDate
import java.time.OffsetDateTime


fun UserProfileEntity.toDomain(): UserProfile {
    return UserProfile(
        id = id,
        name = name,
        birthDate = birthDate,
        avatarUrl = avatarUrl,
        createdAt = createdAt
    )
}



@RequiresApi(Build.VERSION_CODES.O)
fun ProfileResponseDto.toDomain(): UserProfile {
    return UserProfile(
        id = id,
        name = name,
        birthDate = birthDate?.let { LocalDate.parse(it) },
        avatarUrl = avatarUrl?.let { buildUrl(it) },
        createdAt = OffsetDateTime.parse(createdAt).toLocalDate()
    )
}

fun UpdateResponseDto.toDomain() : UpdateProfile {
    Log.e("LOL", status)
    return UpdateProfile(
        status = status == "success"

    )
}

fun UserProfile.toDto(): ProfileResponseDto {
    return ProfileResponseDto(
        id = id,
        name = name,
        birthDate = birthDate?.toString(),
        avatarUrl = avatarUrl?.toString(),
        createdAt = createdAt?.toString()
    )
}

private fun buildUrl(path: String): String {
    return when {
        path.startsWith("http") -> path
        else -> BuildConfig.BASE_URL_AVATAR + path
    }
}
