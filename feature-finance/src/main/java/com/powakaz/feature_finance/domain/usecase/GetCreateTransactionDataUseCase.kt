package com.powakaz.feature_finance.domain.usecase

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.feature_finance.domain.model.CreateTransactionData
import com.powakaz.feature_finance.domain.repository.FinanceRepository
import javax.inject.Inject

class GetCreateTransactionDataUseCase @Inject constructor(private val financeRepository: FinanceRepository) {
    suspend operator fun invoke() : NetworkResult<CreateTransactionData> = financeRepository.getCreateTransactionData()
}