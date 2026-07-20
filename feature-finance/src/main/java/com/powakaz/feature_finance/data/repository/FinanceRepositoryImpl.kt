package com.powakaz.feature_finance.data.repository

import com.powakaz.core_common.repository.UserIdRepository
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.core_network.utils.safeApiCall
import com.powakaz.feature_finance.data.mapper.FinanceDashboardMapper
import com.powakaz.feature_finance.data.remote.NetworkFinanceApi
import com.powakaz.feature_finance.data.remote.model.GetCategoriesDto
import com.powakaz.feature_finance.data.remote.model.GetTransactionsPageDto
import com.powakaz.feature_finance.data.remote.model.GetWalletsDto
import com.powakaz.feature_finance.di.NetworkModule
import com.powakaz.feature_finance.domain.model.FinanceDashboard
import com.powakaz.feature_finance.domain.repository.FinanceRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class FinanceRepositoryImpl @Inject constructor(
    @NetworkModule.AuthenticatedNetworkFinanceApi private val authNetworkFinanceApi: NetworkFinanceApi,
    @NetworkModule.PublicNetworkFinanceApi private val publicNetworkFinanceApi: NetworkFinanceApi,
    private val financeDashboardMapper: FinanceDashboardMapper,
    private val userIdRepository: UserIdRepository
) : FinanceRepository {

    private suspend fun getAllWallets(): NetworkResult<List<GetWalletsDto>> {
        return safeApiCall {
            publicNetworkFinanceApi.getAllWallets()
        }
    }

    private suspend fun getAllCategories(): NetworkResult<List<GetCategoriesDto>> {
        return safeApiCall {
            publicNetworkFinanceApi.getAllCategories()
        }
    }

    private suspend fun getStartTransactionsPage(): NetworkResult<GetTransactionsPageDto> {
        return safeApiCall {
            publicNetworkFinanceApi.getStartTransactionsPage()
        }
    }


    override suspend fun getFinanceDashboard(weeklyWalletId: Int): NetworkResult<FinanceDashboard> {
        return coroutineScope {
            val walletsReq = async { getAllWallets() }
            val transactionsReq = async { getStartTransactionsPage() }
            //val currentUserId = userIdRepository.getUserId().first()

            val wallets = when(val result = walletsReq.await()){
                is NetworkResult.Success<List<GetWalletsDto>> -> result.data
                is NetworkResult.Error -> return@coroutineScope result
                is NetworkResult.Exception -> return@coroutineScope result
            }

            val transactions = when(val result = transactionsReq.await()){
                is NetworkResult.Success<GetTransactionsPageDto> -> result.data
                is NetworkResult.Error -> return@coroutineScope result
                is NetworkResult.Exception -> return@coroutineScope result
            }

            NetworkResult.Success(financeDashboardMapper.map(wallets, transactions, 1, weeklyWalletId))
        }
    }

}