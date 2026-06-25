package com.powakaz.nesttrack.feature_profile.presentation.mapper




import androidx.compose.ui.graphics.Color
import com.powakaz.nesttrack.feature_profile.R
import com.powakaz.nesttrack.feature_profile.presentation.model.AvatarUi
import com.powakaz.nesttrack.feature_profile.presentation.model.ProfileUiState

fun getDefaultAvatar(id : Int): AvatarUi.Default {

    return when(id) {
        1 -> AvatarUi.Default(
            avatarRes = R.drawable.man,
            gradient = listOf(
                Color(0xFFEDF0FF),
                Color(0xFFB7C1FA)
            )
        )

        else -> AvatarUi.Default(
            avatarRes = R.drawable.girl,
            gradient = listOf(
                Color(0xFFFDF0F7),
                Color(0xFFE59DD3)
            )
        )
    }
}

