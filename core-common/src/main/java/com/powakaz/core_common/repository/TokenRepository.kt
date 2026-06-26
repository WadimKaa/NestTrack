package com.powakaz.core_common.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    fun getAccessToken() : Flow<String?>
    suspend fun saveToken(token: String)
    suspend fun clearToken()
}