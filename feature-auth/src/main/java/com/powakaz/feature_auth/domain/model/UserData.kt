package com.powakaz.feature_auth.domain.model

data class UserData(
    val isTokenCorrect : Boolean,
    val id : Int,
    val name : String,
    val avatarUrl : String,
    val birthDate : String
)
