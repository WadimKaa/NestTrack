package com.powakaz.feature_finance.data.remote.model.create_transaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreateTransactionRequestDto(
    @SerialName("from_wallet_id")
    val fromWalletId : Int?,
    @SerialName("to_wallet_id")
    val toWalletId : Int?,
    @SerialName("category_id")
    val categoryId : Int,
    @SerialName("name")
    val name : String,
    @SerialName("description")
    val description : String,
    @SerialName("amount")
    val amount : Float,
    @SerialName("transaction_date")
    val transactionDate : String
)