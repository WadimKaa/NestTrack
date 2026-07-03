package com.powakaz.feature_auth.data.repository

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.core_network.utils.safeApiCall
import com.powakaz.feature_auth.data.mapper.toDomain
import com.powakaz.feature_auth.data.remote.NetworkTokenApi
import com.powakaz.feature_auth.data.remote.model.token_check.TokenCheckBodyRequest
import com.powakaz.feature_auth.domain.model.UserData
import com.powakaz.feature_auth.domain.repository.TokenCheckRepository
import javax.inject.Inject

class TokenCheckImpl @Inject constructor(private val api : NetworkTokenApi) : TokenCheckRepository {

    override suspend fun checkToken(token: String): NetworkResult<UserData> {
        return safeApiCall {
            api.checkToken(TokenCheckBodyRequest(token)).toDomain()
        }
    }
}