package com.powakaz.feature_auth.data.remote.model.token_check

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TokenCheckResponseDto(
    @SerialName("status")
    val status : String,
    @SerialName("user")
    val user : User
)



@Serializable
data class User(
    @SerialName("id")
    val id : Int,
    @SerialName("name")
    val name : String?,
    @SerialName("avatar_url")
    val avatarUrl : String?,
    @SerialName("birth_date")
    val birthDate : String?
)