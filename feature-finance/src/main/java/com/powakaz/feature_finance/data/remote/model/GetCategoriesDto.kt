package com.powakaz.feature_finance.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetCategoriesDto(
    @SerialName("id")
    val id : Int,
    @SerialName("user_id")
    val userId : Int?,
    @SerialName("name")
    val name : String,
    @SerialName("icon_name")
    val iconName : String,
    @SerialName("color_hex")
    val colorHex : String,
    @SerialName("is_income")
    val isIncome : Boolean,
)