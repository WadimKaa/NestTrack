package com.powakaz.nesttrack.feature_profile.presentation.mapper


import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.powakaz.nesttrack.feature_profile.R
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.presentation.model.AvatarUi


fun getDefaultAvatar(id: Int) : AvatarUi{

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

