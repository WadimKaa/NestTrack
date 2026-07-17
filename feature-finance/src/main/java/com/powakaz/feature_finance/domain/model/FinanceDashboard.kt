package com.powakaz.feature_finance.domain.model

import java.time.Instant
import java.time.LocalDate

data class FinanceDashboard(
    val totalBalance: Float,
    val weekBalance: Float,
    val userWalletList: List<Wallet>,
    val financeDays: List<FinanceDay>
)


enum class WalletType { CARD, CASH }
enum class Currency { BYN, USD }

data class Wallet(
    val id: Int,
    val userId: Int?,
    val name: String,
    val description: String,
    val currency: Currency,
    val type: WalletType,
    val balance: Float,
    val iconId: Int,
)


data class FinanceDay(
    val title: String,
    val transactionDate : LocalDate,
    val income: Float,
    val outgo: Float,
    val transactions: List<Transaction>
)



data class Transaction(
    val id : Int,
    val name: String,
    val description: String,
    val categoryName: String,
    val iconId: String,
    val iconCircleColor: String,
    val amount: Float,
    val type: WalletType,
    val transactionDate : Instant
)
