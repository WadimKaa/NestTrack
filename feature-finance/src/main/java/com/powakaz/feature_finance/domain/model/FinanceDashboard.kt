package com.powakaz.feature_finance.domain.model

data class FinanceDashboard(
    val totalBalance: Int,
    val weekBalance: Int,
    val walletDtoList: List<Wallet>,
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
    val balance: String,
    val iconId: Int,
)


data class FinanceDay(
    val title: String,
    val income: Int,
    val outgo: Int,
    val operations: List<Operation>
)


data class Operation(
    val name: String,
    val description: String,
    val iconId: Int,
    val iconCircleColor: String,
    val balance: Int,
    val type: WalletType
)
