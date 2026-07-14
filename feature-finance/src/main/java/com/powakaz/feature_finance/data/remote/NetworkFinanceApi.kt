package com.powakaz.feature_finance.data.remote

import com.powakaz.feature_finance.data.remote.model.GetWalletsDto
import retrofit2.http.GET

interface NetworkFinanceApi {
    @GET("wallets")
    suspend fun getAllWallets(
    ): List<GetWalletsDto>
}