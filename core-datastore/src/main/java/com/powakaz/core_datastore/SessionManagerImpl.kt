package com.powakaz.core_datastore

import com.powakaz.core_common.manager.SessionManager
import com.powakaz.core_common.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManagerImpl @Inject constructor(private val tokenRepository: TokenRepository) :
    SessionManager {

    override fun isLoggedIn(): Flow<Boolean?> {
        return tokenRepository.getAccessToken().map {
            if (it == null) {
                null
            } else if (it.isBlank()) {
                false
            } else {
                true
            }
        }
    }

    override suspend fun logout() {
        tokenRepository.clearToken()
    }
}