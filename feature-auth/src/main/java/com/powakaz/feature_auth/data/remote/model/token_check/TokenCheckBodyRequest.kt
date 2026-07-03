package com.powakaz.feature_auth.data.remote.model.token_check

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenCheckBodyRequest(
    @SerialName("token")
    val token : String
)