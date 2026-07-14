package com.powakaz.feature_finance.domain.usecase

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.feature_finance.domain.model.FinanceDashboard
import com.powakaz.feature_finance.domain.model.Wallet
import com.powakaz.feature_finance.domain.repository.FinanceRepository
import javax.inject.Inject

class GetFinancialDashboardUseCase @Inject constructor(private val financeRepository: FinanceRepository) {
    suspend operator fun invoke(): NetworkResult<List<Wallet>> = financeRepository.getAllWallets()
}