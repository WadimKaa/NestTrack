package com.powakaz.feature_finance.data.repository

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.core_network.utils.safeApiCall
import com.powakaz.feature_finance.data.mapper.toDomain
import com.powakaz.feature_finance.data.remote.NetworkFinanceApi
import com.powakaz.feature_finance.di.NetworkModule
import com.powakaz.feature_finance.domain.model.Wallet
import com.powakaz.feature_finance.domain.repository.FinanceRepository
import javax.inject.Inject


class FinanceRepositoryImpl @Inject constructor(
    @NetworkModule.AuthenticatedNetworkFinanceApi private val authNetworkFinanceApi: NetworkFinanceApi,
    @NetworkModule.PublicNetworkFinanceApi private val publicNetworkFinanceApi: NetworkFinanceApi
) : FinanceRepository {

    override suspend fun getAllWallets(): NetworkResult<List<Wallet>> {
        return safeApiCall {
            publicNetworkFinanceApi.getAllWallets().map { it.toDomain() }
        }
    }
}