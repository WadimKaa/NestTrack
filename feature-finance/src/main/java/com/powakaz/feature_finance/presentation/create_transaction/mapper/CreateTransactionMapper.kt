package com.powakaz.feature_finance.presentation.create_transaction.mapper

import com.powakaz.feature_finance.domain.model.CreateTransaction
import com.powakaz.feature_finance.presentation.create_transaction.model.CreateTransactionUiState


fun CreateTransactionUiState.toDomain(): CreateTransaction {
    return CreateTransaction(
        fromWalletId = this.fromWallet.id,
        toWalletId = this.toWallet.id,
        categoryId = this.selectedCategoryIndex,
        name = this.name,
        description = "",
        amount = this.amount.toFloat(),
        transactionDate = this.selectedDate
    )
}
