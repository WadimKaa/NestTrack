package com.powakaz.nesttrack.feature_profile.presentation.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class AvatarUi(
    @DrawableRes val avatarRes: Int,
    val gradient: List<Color>
)