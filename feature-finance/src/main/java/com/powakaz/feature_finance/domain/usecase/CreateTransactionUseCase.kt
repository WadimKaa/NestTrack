package com.powakaz.feature_finance.domain.usecase

import com.powakaz.feature_finance.domain.model.CreateTransaction
import com.powakaz.feature_finance.domain.repository.FinanceRepository
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(private val repository: FinanceRepository) {
    suspend operator fun invoke(createTransaction: CreateTransaction) = repository.createTransaction(createTransaction)
}