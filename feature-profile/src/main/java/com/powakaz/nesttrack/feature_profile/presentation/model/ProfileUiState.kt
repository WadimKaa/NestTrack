package com.powakaz.nesttrack.feature_profile.presentation.model

data class ProfileUiState(
    val id: Int,
    val name: String,
    val birthDate: String,
    val createdAt: String,
    val avatar: AvatarUi
) {
}