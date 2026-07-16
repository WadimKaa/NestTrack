package com.powakaz.feature_finance.domain.repository

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.feature_finance.domain.model.FinanceDashboard

interface FinanceRepository {
    suspend fun getFinanceDashboard(currentUserId: Int, weeklyWalletId: Int) : NetworkResult<FinanceDashboard>
}