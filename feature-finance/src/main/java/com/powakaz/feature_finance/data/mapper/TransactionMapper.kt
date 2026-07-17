package com.powakaz.feature_finance.data.mapper

import com.powakaz.feature_finance.data.remote.model.GetWalletsDto
import com.powakaz.feature_finance.data.remote.model.TransactionDto
import com.powakaz.feature_finance.domain.model.Transaction
import com.powakaz.feature_finance.domain.model.WalletType
import java.time.Instant
import javax.inject.Inject

class TransactionMapper @Inject constructor() {


    fun map(transactionDto: TransactionDto, wallets: List<GetWalletsDto>): Transaction {
        return Transaction(
            id = transactionDto.id,
            name = transactionDto.name,
            description = transactionDto.description,
            categoryName = transactionDto.categoryName,
            iconId = transactionDto.categoryIcon,
            iconCircleColor = transactionDto.categoryColor,
            amount = transactionDto.amount,
            type = getType(wallets, transactionDto),
            transactionDate = Instant.parse(transactionDto.transactionDate)
        )
    }

    private fun getType(
        wallets: List<GetWalletsDto>,
        transactionDto: TransactionDto
    ): WalletType {
        val from = transactionDto.fromWalletName
        val to = transactionDto.toWalletName

        val nameForFind = to ?: from

        val typeDto = wallets.find { it.name == nameForFind!! }!!.type
        return WalletType.valueOf(typeDto.uppercase())
    }
}