package com.powakaz.feature_finance.domain.repository

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.feature_finance.domain.model.Wallet

interface FinanceRepository {
    suspend fun getAllWallets() : NetworkResult<List<Wallet>>
}