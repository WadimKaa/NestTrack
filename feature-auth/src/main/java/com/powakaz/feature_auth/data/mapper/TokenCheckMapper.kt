package com.powakaz.feature_auth.data.mapper

import com.powakaz.feature_auth.data.remote.model.token_check.TokenCheckResponseDto
import com.powakaz.feature_auth.domain.model.UserData


fun TokenCheckResponseDto.toDomain(): UserData {
    return UserData(
        isTokenCorrect = this.status == "success",
        id = this.user.id,
        name = this.user.name ?: "",
        avatarUrl = this.user.avatarUrl ?: "",
        birthDate = this.user.birthDate ?: ""
    )
}