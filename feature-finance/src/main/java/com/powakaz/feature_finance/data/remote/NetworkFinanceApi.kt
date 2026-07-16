package com.powakaz.feature_finance.data.remote

import com.powakaz.feature_finance.data.remote.model.GetCategoriesDto
import com.powakaz.feature_finance.data.remote.model.GetTransactionsPageDto
import com.powakaz.feature_finance.data.remote.model.GetWalletsDto
import retrofit2.http.GET

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
}