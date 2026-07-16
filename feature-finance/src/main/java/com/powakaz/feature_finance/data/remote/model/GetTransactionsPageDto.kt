package com.powakaz.feature_finance.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetTransactionsPageDto(
    @SerialName("page")
    val page : Int,
    @SerialName("total_records")
    val totalRecords : Int,
    @SerialName("has_more")
    val hasMore : Boolean,
    @SerialName("data")
    val data : List<TransactionDto>,
)



@Serializable
data class TransactionDto(
    @SerialName("id")
    val id : Int,
    @SerialName("from_wallet_name")
    val fromWalletName : String?,
    @SerialName("to_wallet_name")
    val toWalletName : String?,
    @SerialName("category_name")
    val categoryName : String,
    @SerialName("category_icon")
    val categoryIcon : String,
    @SerialName("category_color")
    val categoryColor : String,
    @SerialName("name")
    val name : String,
    @SerialName("description")
    val description : String,
    @SerialName("amount")
    val amount : Float,
    @SerialName("created_at")
    val createdAt : String,
    @SerialName("transaction_date")
    val transactionDate : String,
)