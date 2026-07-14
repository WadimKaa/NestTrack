package com.powakaz.feature_finance.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetWalletsDto(
    @SerialName("id")
    val id : Int,
    @SerialName("user_id")
    val userId : Int?,
    @SerialName("name")
    val name : String,
    @SerialName("description")
    val description : String,
    @SerialName("currency")
    val currency : String,
    @SerialName("type")
    val type : String,
    @SerialName("is_active")
    val isActive : Boolean,
    @SerialName("balance")
    val balance : String
)