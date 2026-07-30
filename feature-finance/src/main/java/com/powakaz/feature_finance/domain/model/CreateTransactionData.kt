package com.powakaz.feature_finance.domain.model

data class CreateTransactionData(
    val userId : Int,
    val wallets : List<Wallet>,
    val categories : List<Category>
)