package com.powakaz.nesttrack.feature_time.data.mapper

import android.content.Context
import android.content.res.Resources
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.powakaz.nesttrack.feature_time.BuildConfig
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.activities.ActivitiesResponseDto
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.ConcessionDto
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.ConcessionListResponseDto
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.TimeBalanceResponseDto
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.activities.CreateActivitiesRequestDto
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.activities.CreateActivitiesResponseDto
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.avatar.UserProfileResponseDto
import com.powakaz.nesttrack.feature_time.domain.model.activities.Activities
import com.powakaz.nesttrack.feature_time.domain.model.Concession
import com.powakaz.nesttrack.feature_time.domain.model.ConcessionList
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesRequest
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesResponse
import com.powakaz.nesttrack.feature_time.domain.model.avatar.Avatar
import com.powakaz.nesttrack.feature_time.domain.model.avatar.UserProfile
import java.time.OffsetDateTime

fun TimeBalanceResponseDto.toDomain(): TimeBalance {
    return TimeBalance(
        userIdWith = userIdWith,
        userNameWith = userNameWith,
        balanceHours = balanceHours
    )
}

fun UserProfileResponseDto.toDomain() : UserProfile {
    return UserProfile(
        id = id,
        name = name,
        avatarUrl = if (avatarUrl == null || avatarUrl == "" || avatarUrl == "NULL") {
            Avatar.Default
        } else {
            Avatar.Remote(buildUrl(avatarUrl))
        },
    )
}
private fun buildUrl(path: String): String {
    if (path.startsWith("http")) return path

    val baseUrl = BuildConfig.BASE_URL_AVATAR.removeSuffix("/")
    val relativePath = if (path.startsWith("/")) path else "/$path"

    return baseUrl + relativePath
}

/*private fun buildUrl(path: String): String {
    return when {
        path.startsWith("http") -> path
        else -> BuildConfig.BASE_URL_AVATAR + path
    }
}*/

////

fun ActivitiesResponseDto.toDomain(): Activities {
    return Activities(
        id = id,
        name = name,
        iconName = iconName,
        iconColor = iconColor
    )
}

fun CreateActivitiesRequest.toDto(): CreateActivitiesRequestDto {
    return CreateActivitiesRequestDto(
        name = name,
        iconName = iconName.toString(),
        iconColor = iconColor.toString()
    )
}

fun CreateActivitiesResponseDto.toDomain() : CreateActivitiesResponse {
    return CreateActivitiesResponse(
        id = id,
        status = status == "success"
    )
}

fun Context.findActivitiesIconToServerName(
    @DrawableRes drawableRes: Int
): String {
    return resources
        .getResourceEntryName(drawableRes)
        .removePrefix("ic_")
        .removeSuffix("_activities")
}


fun Color.findActivitiesColorToServer(): String {
    return "#%06X".format(0xFFFFFF and this.toArgb())
}



////


@RequiresApi(Build.VERSION_CODES.O)
fun ConcessionListResponseDto.toDomain(): ConcessionList {
    return ConcessionList(
        page = page,
        totalRecords = totalRecords,
        hasMorePage = hasMorePage,
        dataConcession = dataConcession.map {
            it.toDomain()
        }
    )
}
@RequiresApi(Build.VERSION_CODES.O)
fun ConcessionDto.toDomain(): Concession {
    return Concession(
        id = id,
        giverName = giverName,
        receiverName = receiverName,
        activityName = activityName,
        activityIcon = activityIcon,
        activityIconColor = activityIconColor,
        durationHours = durationHours,
        description = description,
        createdAt = createdAt,
        activityDate = OffsetDateTime.parse(activityDate).toLocalDate()
    )
}
