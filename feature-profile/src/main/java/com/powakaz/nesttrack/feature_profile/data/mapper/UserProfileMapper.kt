package com.powakaz.nesttrack.feature_profile.data.mapper

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.intl.Locale
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


private const val SERVER_DATE_FORMAT = "yyyy-MM-dd"

@RequiresApi(Build.VERSION_CODES.O)
fun ProfileDto.toDomain(): UserProfile {
    return UserProfile(
        id = id,
        name = name,
        birthDate = parseServerDate(birthDate),
        avatarUrl = avatarUrl,
        createdAt = OffsetDateTime.parse(createdAt).toLocalDate()
    )
}


@SuppressLint("SimpleDateFormat")
private fun parseServerDate(dateStr: String?): Long? {
    if (dateStr == null) return null

    return try {
        val formatter = SimpleDateFormat(SERVER_DATE_FORMAT)
        formatter.parse(dateStr)?.time
    } catch (e: Exception) {
        null
    }
}
