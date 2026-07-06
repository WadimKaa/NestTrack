package com.powakaz.core_common.repository

import kotlinx.coroutines.flow.Flow

interface UserIdRepository {
    fun getUserId() : Flow<Int>
    suspend fun saveUserId(userId : Int)
    suspend fun clearUserId()
}