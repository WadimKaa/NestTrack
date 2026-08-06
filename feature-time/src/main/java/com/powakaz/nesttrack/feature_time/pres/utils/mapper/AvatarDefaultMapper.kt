package com.powakaz.nesttrack.feature_time.pres.utils.mapper

import androidx.compose.ui.graphics.Color
import com.powakaz.nesttrack.feature_time.R
import com.powakaz.nesttrack.feature_time.domain.model.avatar.Avatar
import com.powakaz.nesttrack.feature_time.pres.model.AvatarUi

fun mapDefaultAvatar(id: Int) : AvatarUi {

    return when (id) {
        1 -> AvatarUi(
            avatarRes = R.drawable.man,
            gradient = listOf(
                Color(0xFFEDF0FF),
                Color(0xFFB7C1FA)
            )
        )

        else -> AvatarUi(
            avatarRes = R.drawable.girl,
            gradient = listOf(
                Color(0xFFFDF0F7),
                Color(0xFFE59DD3)
            )
        )
    }
}