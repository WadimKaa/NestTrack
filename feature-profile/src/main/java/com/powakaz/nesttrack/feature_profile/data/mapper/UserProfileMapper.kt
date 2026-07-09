package com.powakaz.nesttrack.feature_profile.data.mapper

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.intl.Locale
import com.powakaz.nesttrack.feature_profile.BuildConfig
import com.powakaz.nesttrack.feature_profile.data.datasourse.local.entites.UserProfileEntity
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.ProfileDto
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.Date


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
fun ProfileDto.toDomain(): UserProfile {
    return UserProfile(
        id = id,
        name = name,
        birthDate = birthDate?.let { LocalDate.parse(it) },
        avatarUrl = avatarUrl?.let { buildUrl(it) },
        createdAt = OffsetDateTime.parse(createdAt).toLocalDate()
    )
}

private fun buildUrl(path: String): String {
    return when {
        path.startsWith("http") -> path
        else -> BuildConfig.BASE_URL_AVATAR + path
    }
}
