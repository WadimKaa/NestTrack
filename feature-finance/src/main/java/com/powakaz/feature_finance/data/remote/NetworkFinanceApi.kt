package com.powakaz.feature_finance.data.remote

import com.powakaz.feature_finance.data.remote.model.GetCategoriesDto
import com.powakaz.feature_finance.data.remote.model.GetTransactionsPageDto
import com.powakaz.feature_finance.data.remote.model.GetWalletsDto
import com.powakaz.feature_finance.data.remote.model.create_transaction.CreateTransactionRequestDto
import com.powakaz.feature_finance.data.remote.model.create_transaction.CreateTransactionResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkFinanceApi {
    @GET("wallets")
    suspend fun getAllWallets(
    ): List<GetWalletsDto>


    @GET("categories")
    suspend fun getAllCategories(
    ): List<GetCategoriesDto>


    @GET("transactions?page=1")
    suspend fun getStartTransactionsPage(
    ): GetTransactionsPageDto


    @POST("transactions")
    suspend fun createTransaction(@Body createTransactionRequestDto: CreateTransactionRequestDto) : CreateTransactionResponseDto
}