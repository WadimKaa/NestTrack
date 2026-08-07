package com.powakaz.feature_finance.data.mapper

import com.powakaz.feature_finance.data.remote.model.create_transaction.CreateTransactionRequestDto
import com.powakaz.feature_finance.domain.model.CreateTransaction
import java.time.LocalDate
import javax.inject.Inject


fun CreateTransaction.toDto(): CreateTransactionRequestDto {
    return CreateTransactionRequestDto(
        fromWalletId = this.fromWalletId,
        toWalletId = this.toWalletId,
        categoryId = this.categoryId,
        name = this.name,
        description = this.description,
        amount = this.amount,
        transactionDate = this.toString()
    )
}


