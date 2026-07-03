package com.powakaz.feature_auth.domain.repository

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.feature_auth.domain.model.UserData

interface TokenCheckRepository {
    suspend fun checkToken(token : String) : NetworkResult<UserData>
}