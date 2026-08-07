package com.powakaz.feature_finance.data.repository

import android.util.Log
import com.powakaz.core_common.repository.UserIdRepository
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.core_network.utils.safeApiCall
import com.powakaz.feature_finance.data.mapper.FinanceDashboardMapper
import com.powakaz.feature_finance.data.mapper.toDomain
import com.powakaz.feature_finance.data.mapper.toDto
import com.powakaz.feature_finance.data.remote.NetworkFinanceApi
import com.powakaz.feature_finance.data.remote.model.GetCategoriesDto
import com.powakaz.feature_finance.data.remote.model.GetTransactionsPageDto
import com.powakaz.feature_finance.data.remote.model.GetWalletsDto
import com.powakaz.feature_finance.data.remote.model.create_transaction.CreateTransactionResponseDto
import com.powakaz.feature_finance.di.NetworkModule
import com.powakaz.feature_finance.domain.model.CreateTransaction
import com.powakaz.feature_finance.domain.model.CreateTransactionData
import com.powakaz.feature_finance.domain.model.FinanceDashboard
import com.powakaz.feature_finance.domain.model.Transaction
import com.powakaz.feature_finance.domain.model.Wallet
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
            val currentUserId = userIdRepository.getUserId().first()

            val wallets = when (val result = walletsReq.await()) {
                is NetworkResult.Success<List<GetWalletsDto>> -> result.data
                is NetworkResult.Error -> return@coroutineScope result
                is NetworkResult.Exception -> return@coroutineScope result
            }

            val transactions = when (val result = transactionsReq.await()) {
                is NetworkResult.Success<GetTransactionsPageDto> -> result.data
                is NetworkResult.Error -> return@coroutineScope result
                is NetworkResult.Exception -> return@coroutineScope result
            }

            NetworkResult.Success(
                financeDashboardMapper.map(
                    wallets,
                    transactions,
                    currentUserId,
                    weeklyWalletId
                )
            )
        }
    }

    override suspend fun getCreateTransactionData(): NetworkResult<CreateTransactionData> {
        return coroutineScope {
            val walletsReq = async { getAllWallets() }
            val categoriesReq = async { getAllCategories() }
            val currentUserId = userIdRepository.getUserId().first()

            val wallets = when (val result = walletsReq.await()) {
                is NetworkResult.Success<List<GetWalletsDto>> -> result.data
                is NetworkResult.Error -> return@coroutineScope result
                is NetworkResult.Exception -> return@coroutineScope result
            }

            val categories = when (val result = categoriesReq.await()) {
                is NetworkResult.Success<List<GetCategoriesDto>> -> result.data
                is NetworkResult.Error -> return@coroutineScope result
                is NetworkResult.Exception -> return@coroutineScope result
            }

            NetworkResult.Success(
                CreateTransactionData(
                    userId = currentUserId,
                    wallets = wallets.map { it.toDomain() } + Wallet.getExternalWallet(),
                    categories = categories.map { it.toDomain() }
                )
            )
        }
    }

    override suspend fun createTransaction(createTransaction: CreateTransaction) {
        coroutineScope {
            val result = safeApiCall {
                authNetworkFinanceApi.createTransaction(createTransaction.toDto())
            }
        }
    }
}