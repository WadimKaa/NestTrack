package com.powakaz.feature_finance.domain.model

data class FinanceDashboard(
    val totalBalance: Int,
    val weekBalance: Int,
    val walletList: List<Wallet>,
    val financeDays: List<FinanceDay>
)


enum class WalletType { CASH, CARD }

data class Wallet(
    val type: WalletType,
    val balance: Int,
    val iconId: Int,
    val description: String
)


data class FinanceDay(
    val title: String,
    val income: Int,
    val outgo: Int,
    val operations: List<Operation>
)


data class Operation (
    val name : String,
    val description: String,
    val iconId : Int,
    val iconCircleColor : String,
    val balance : Int,
    val type : WalletType
)
