package com.powakaz.feature_finance.domain.repository

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.feature_finance.domain.model.CreateTransaction
import com.powakaz.feature_finance.domain.model.CreateTransactionData
import com.powakaz.feature_finance.domain.model.FinanceDashboard
import com.powakaz.feature_finance.domain.model.Transaction

interface FinanceRepository {
    suspend fun getFinanceDashboard(weeklyWalletId: Int) : NetworkResult<FinanceDashboard>
    suspend fun getCreateTransactionData() : NetworkResult<CreateTransactionData>
    suspend fun createTransaction(createTransaction: CreateTransaction)
}