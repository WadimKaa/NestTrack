package com.powakaz.feature_finance.data.mapper

import com.powakaz.feature_finance.data.remote.model.GetTransactionsPageDto
import com.powakaz.feature_finance.data.remote.model.GetWalletsDto
import com.powakaz.feature_finance.data.remote.model.TransactionDto
import com.powakaz.feature_finance.domain.model.FinanceDashboard
import com.powakaz.feature_finance.domain.model.FinanceDay
import com.powakaz.feature_finance.domain.model.Transaction
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FinanceDashboardMapper @Inject constructor(private val transactionMapper: TransactionMapper) {


    fun map(
        walletsDto: List<GetWalletsDto>,
        transactionsDto: GetTransactionsPageDto,
        currentUserId: Int,
        weeklyWalletId: Int,
    ): FinanceDashboard {
        return FinanceDashboard(
            totalBalance = calculateTotalBalance(walletsDto, currentUserId),
            weekBalance = calculateWeekBalance(walletsDto, weeklyWalletId),
            userWalletList = walletsDto.filter { it.userId == currentUserId }.map { it.toDomain() },
            financeDays = bindFinanceDays(transactionsDto, walletsDto, currentUserId),
            currentUserId = currentUserId
        )
    }

    private fun bindFinanceDays(
        transactionsDto: GetTransactionsPageDto,
        walletsDto: List<GetWalletsDto>,
        currentUserId: Int
    ): List<FinanceDay> {
        val filteredTransactions = filterByCurrentUser(transactionsDto, walletsDto, currentUserId)
        val transactions =
            filteredTransactions.map { transactionMapper.map(it, walletsDto, currentUserId) }
        val transactionsGroups = transactions.groupBy {
            it.transactionDate.atZone(ZoneId.systemDefault()).toLocalDate()
        }

        var financeDays = listOf<FinanceDay>()

        transactionsGroups.forEach { (date, transactions) ->
            financeDays = financeDays + mapTransactionToFinanceDay(transactions, date)
        }

        return financeDays
    }

    private fun filterByCurrentUser(
        transactionsDto: GetTransactionsPageDto,
        walletsDto: List<GetWalletsDto>,
        currentUserId: Int
    ): List<TransactionDto> {

        val walletUserIds = walletsDto.associate { it.name to it.userId }

        return transactionsDto.data.filter { dto ->
            walletUserIds[dto.fromWalletName] == currentUserId ||
                    walletUserIds[dto.toWalletName] == currentUserId
        }
    }


    private fun mapTransactionToFinanceDay(
        transactionsGroup: List<Transaction>,
        date: LocalDate
    ): FinanceDay {
        return FinanceDay(
            title = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
            transactionDate = date,
            income = transactionsGroup.filter { it.amount > 0 }.sumOf { it.amount.toDouble() }
                .toFloat(),
            outgo = transactionsGroup.filter { it.amount < 0 }.sumOf { it.amount.toDouble() }
                .toFloat(),
            transactions = transactionsGroup
        )
    }


    private fun calculateWeekBalance(wallets: List<GetWalletsDto>, weeklyWalletId: Int): Float =
        wallets.find { it.id == weeklyWalletId }!!.balance

    private fun calculateTotalBalance(wallets: List<GetWalletsDto>, currentUserId: Int): Float =
        wallets.filter { it.userId == currentUserId }.sumOf { it.balance.toDouble() }.toFloat()

}