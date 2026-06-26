package com.powakaz.core_common.manager

import kotlinx.coroutines.flow.Flow

interface SessionManager {
    fun isLoggedIn(): Flow<Boolean>
    suspend fun logout()
}