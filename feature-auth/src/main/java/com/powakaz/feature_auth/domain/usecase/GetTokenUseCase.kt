package com.powakaz.feature_auth.domain.usecase

import com.powakaz.core_common.repository.TokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val tokenRepository: TokenRepository) {
    operator fun invoke() = tokenRepository.getAccessToken()
}