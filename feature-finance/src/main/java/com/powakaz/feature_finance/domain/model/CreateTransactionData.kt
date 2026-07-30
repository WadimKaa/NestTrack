package com.powakaz.feature_finance.domain.model

data class CreateTransactionData(
    val userId : Int,
    val wallets : List<Wallet>,
    val categories : List<Category>
)


data class Category(
    val id: Int,
    val userId: Int?,
    val name: String,
    val iconName: String,
    val colorHex: String
)