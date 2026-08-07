package com.powakaz.feature_finance.data.remote.model.create_transaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreateTransactionResponseDto(
    @SerialName("id")
    val id : Int,
    @SerialName("status")
    val status : String
)