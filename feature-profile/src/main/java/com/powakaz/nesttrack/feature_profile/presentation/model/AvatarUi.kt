package com.powakaz.nesttrack.feature_profile.presentation.model


import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

sealed interface AvatarUi {

    data class Custom(
        val url: String
    ) : AvatarUi

    data class Default(
        @DrawableRes val avatarRes: Int,
        val gradient: List<Color>
    ) : AvatarUi
}