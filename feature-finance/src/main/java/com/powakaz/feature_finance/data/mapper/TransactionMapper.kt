package com.powakaz.feature_finance.data.mapper

import com.powakaz.feature_finance.data.remote.model.GetWalletsDto
import com.powakaz.feature_finance.data.remote.model.TransactionDto
import com.powakaz.feature_finance.domain.model.Transaction
import com.powakaz.feature_finance.domain.model.WalletType
import java.time.Instant
import javax.inject.Inject

class TransactionMapper @Inject constructor() {


    fun map(transactionDto: TransactionDto, wallets: List<GetWalletsDto>, currentUserId: Int): Transaction {
        return Transaction(
            id = transactionDto.id,
            name = transactionDto.name,
            description = transactionDto.description,
            categoryName = transactionDto.categoryName,
            iconId = transactionDto.categoryIcon,
            iconResourceId = -1,
            iconCircleColor = transactionDto.categoryColor,
            amount = calculateAmount(currentUserId, transactionDto, wallets),
            type = getType(wallets, transactionDto),
            transactionDate = Instant.parse(transactionDto.transactionDate)
        )
    }

    private fun calculateAmount(
        currentUserId: Int,
        transactionDto: TransactionDto,
        wallets: List<GetWalletsDto>
    ): Float {
        val toWalletId = wallets.find { it.name == transactionDto.toWalletName }?.id
        var amount = transactionDto.amount

        return if (toWalletId == null || wallets.find { it.id == toWalletId && it.userId != currentUserId} != null){
            0 - amount
        }else{
            amount
        }
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